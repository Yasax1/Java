/*
 * $Id: Div.java 451544 2006-09-30 05:38:02Z mrdon $
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
package org.apache.struts2.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.views.util.UrlHelper;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * <!-- START SNIPPET: javadoc -->
 * The div tag is primarily an AJAX tag, providing a remote call from the current page to update a section
 * of content without having to refresh the entire page.<p/>
 *
 * It creates a HTML &lt;DIV /&gt; that obtains it's content via a remote XMLHttpRequest call
 * via the dojo framework.<p/>
 *
 * If a "listenTopics" is supplied, it will listen to that topic and refresh it's content when any message
 * is received.<p/>
 * <!-- END SNIPPET: javadoc -->
 *
 * <b>Important:</b> Be sure to setup the page containing this tag to be Configured for AJAX</p>
 *
 * <p/> <b>Examples</b>
 *
 * <pre>
 * <!-- START SNIPPET: example -->
 * &lt;s:div ... /&gt;
 * <!-- END SNIPPET: example -->
 * </pre>
 *
 * @s.tag name="div" tld-body-content="JSP" tld-tag-class="org.apache.struts2.views.jsp.ui.DivTag"
 * description="Render HTML div providing content from remote call via AJAX"
  */
public class Div extends RemoteCallUIBean {
	
	private static final Log _log = LogFactory.getLog(Div.class);
	

    public static final String TEMPLATE = "div";
    public static final String TEMPLATE_CLOSE = "div-close";
    public static final String COMPONENT_NAME = Div.class.getName();

    protected String updateFreq;
    protected String delay;
    protected String loadingText;
    protected String listenTopics;

    public Div(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    public String getDefaultOpenTemplate() {
        return TEMPLATE;
    }

    protected String getDefaultTemplate() {
        return TEMPLATE_CLOSE;
    }

    public void evaluateExtraParams() {
        super.evaluateExtraParams();

        if (null != updateFreq && !"".equals(updateFreq)) {
            addParameter("updateFreq", findString(updateFreq));
        } else {
            addParameter("updateFreq", "0");
        }

        if (null != delay && !"".equals(delay)) {
            addParameter("delay", findString(delay));
        } else {
            addParameter("delay", "0");
        }
        
        String tmpUpdateFreq = (String) getParameters().get("delay");
        String tmpDelay = (String) getParameters().get("updateFreq");
        try {
        	int _updateFreq = Integer.parseInt(tmpUpdateFreq);
        	int _delay = Integer.parseInt(tmpDelay);
        	
        	if (_updateFreq <= 0 && _delay <= 0) {
        		addParameter("autoStart", "false");
        	}
        }
        catch(NumberFormatException e) {
        	// too bad, invalid updateFreq or delay provided, we
        	// can't determine autoStart mode.
        	_log.info("error while parsing updateFreq ["+tmpUpdateFreq+"] or delay ["+tmpDelay+"] to integer, cannot determine autoStart mode", e);
        }

        if (loadingText != null) {
            addParameter("loadingText", findString(loadingText));
        }

        if (listenTopics != null) {
            addParameter("listenTopics", findString(listenTopics));
        }

        if (href != null) {

            // This is needed for portal and DOJO ajax stuff!
            addParameter("href", null);
            addParameter("href", UrlHelper.buildUrl(findString(href), request, response, null));
        }
    }

    /**
     * How often to re-fetch the content (in milliseconds)
     * @s.tagattribute required="false" type="Integer" default="0"
     */
    public void setUpdateFreq(String updateFreq) {
        this.updateFreq = updateFreq;
    }

    /**
     * How long to wait before fetching the content (in milliseconds)
     * @s.tagattribute required="false" type="Integer" default="0"
     */
    public void setDelay(String delay) {
        this.delay = delay;
    }

    /**
     * The text to display to the user while the new content is being fetched (especially good if the content will take awhile)
     * @s.tagattribute required="false" rtexprvalue="true"
     */
    public void setLoadingText(String loadingText) {
        this.loadingText = loadingText;
    }

    /**
     * Topic name to listen to (comma delimited), that will cause the DIV's content to be re-fetched
     * @s.tagattribute required="false"
     */
    public void setListenTopics(String listenTopics) {
        this.listenTopics = listenTopics;
    }

}
