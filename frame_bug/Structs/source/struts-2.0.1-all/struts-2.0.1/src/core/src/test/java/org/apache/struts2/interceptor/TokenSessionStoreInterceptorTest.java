/*
 * $Id: TokenSessionStoreInterceptorTest.java 439747 2006-09-03 09:22:46Z mrdon $
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
package org.apache.struts2.interceptor;

import org.apache.struts2.TestConfigurationProvider;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

/**
 * Unit test for {@link TokenSessionStoreInterceptor}.
 *
 */
public class TokenSessionStoreInterceptorTest extends TokenInterceptorTest {

    public void testCAllExecute2Times() throws Exception {
        ActionProxy proxy = buildProxy(getActionName());
        setToken(request);
        assertEquals(Action.SUCCESS, proxy.execute());

        ActionProxy proxy2 = buildProxy(getActionName());
        // must not call setToken
        // double post will just return success and render the same view as the first execute
        // see TokenInterceptor where a 2nd call will return invalid.token code instead
        assertEquals(Action.SUCCESS, proxy2.execute());
    }

    protected String getActionName() {
        return TestConfigurationProvider.TOKEN_SESSION_ACTION_NAME;
    }

}
