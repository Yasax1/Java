/*
 * $Id: TokenHelper.java 441909 2006-09-10 05:28:16Z mrdon $
 *
 * Copyright 2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.struts2.util;

import java.math.BigInteger;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

/**
 * TokenHelper
 *
 */
public class TokenHelper {

    /**
     * The default name to map the token value
     */
    public static final String DEFAULT_TOKEN_NAME = "struts.token";
    
    /**
     * The name of the field which will hold the token name
     */
    public static final String TOKEN_NAME_FIELD = "struts.token.name";
    private static final Log LOG = LogFactory.getLog(TokenHelper.class);
    private static final Random RANDOM = new Random();


    /**
     * Sets a transaction token into the session using the default token name.
     *
     * @return the token string
     */
    public static String setToken() {
        return setToken(DEFAULT_TOKEN_NAME);
    }

    /**
     * Sets a transaction token into the session using the provided token name.
     *
     * @param tokenName the name to store into the session with the token as the value
     * @return the token string
     */
    public static String setToken(String tokenName) {
        Map session = ActionContext.getContext().getSession();
        String token = generateGUID();
        try {
        	session.put(tokenName, token);
        }
        catch(IllegalStateException e) {
        	// WW-1182 explain to user what the problem is
        	String msg = "Error creating HttpSession due response is commited to client. You can use the CreateSessionInterceptor or create the HttpSession from your action before the result is rendered to the client: " + e.getMessage();
        	LOG.error(msg, e);
        	throw new IllegalArgumentException(msg);
        }

        return token;
    }

    
    /**
     * Gets a transaction token into the session using the default token name.
     * 
     * @return token
     */
    public static String getToken() {
    	return getToken(DEFAULT_TOKEN_NAME);
    }
    
    /**
     * Gets the Token value from the params in the ServletActionContext using the given name
     *
     * @param tokenName the name of the parameter which holds the token value
     * @return the token String or null, if the token could not be found
     */
    public static String getToken(String tokenName) {
        Map params = ActionContext.getContext().getParameters();
        String[] tokens = (String[]) params.get(tokenName);
        String token;

        if ((tokens == null) || (tokens.length < 1)) {
            LOG.warn("Could not find token mapped to token name " + tokenName);

            return null;
        }

        token = tokens[0];

        return token;
    }

    /**
     * Gets the token name from the Parameters in the ServletActionContext
     *
     * @return the token name found in the params, or null if it could not be found
     */
    public static String getTokenName() {
        Map params = ActionContext.getContext().getParameters();

        if (!params.containsKey(TOKEN_NAME_FIELD)) {
            LOG.warn("Could not find token name in params.");

            return null;
        }

        String[] tokenNames = (String[]) params.get(TOKEN_NAME_FIELD);
        String tokenName;

        if ((tokenNames == null) || (tokenNames.length < 1)) {
            LOG.warn("Got a null or empty token name.");

            return null;
        }

        tokenName = tokenNames[0];

        return tokenName;
    }

    /**
     * Checks for a valid transaction token in the current request params. If a valid token is found, it is
     * removed so the it is not valid again.
     *
     * @return false if there was no token set into the params (check by looking for {@link #TOKEN_NAME_FIELD}), true if a valid token is found
     */
    public static boolean validToken() {
        String tokenName = getTokenName();

        if (tokenName == null) {
        	if (LOG.isDebugEnabled())
        		LOG.debug("no token name found -> Invalid token ");
            return false;
        }

        String token = getToken(tokenName);

        if (token == null) {
        	if (LOG.isDebugEnabled()) 
        		LOG.debug("no token found for token name "+tokenName+" -> Invalid token ");
            return false;
        }

        Map session = ActionContext.getContext().getSession();
        String sessionToken = (String) session.get(tokenName);

        if (!token.equals(sessionToken)) {
            LOG.warn(LocalizedTextUtil.findText(TokenHelper.class, "struts.internal.invalid.token", ActionContext.getContext().getLocale(), "Form token {0} does not match the session token {1}.", new Object[]{
                    token, sessionToken
            }));

            return false;
        }

        // remove the token so it won't be used again
        session.remove(tokenName);

        return true;
    }
    
    public static String generateGUID() {
        return new BigInteger(165, RANDOM).toString(36).toUpperCase();
    }
}
