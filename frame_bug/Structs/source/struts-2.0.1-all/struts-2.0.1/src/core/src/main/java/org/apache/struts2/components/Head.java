/*
 * $Id: Head.java 451544 2006-09-30 05:38:02Z mrdon $
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

import org.apache.struts2.StrutsConstants;
import org.apache.struts2.config.Settings;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * <!-- START SNIPPET: javadoc -->
 *
 * Renders parts of the HEAD section for an HTML file. This is useful as some themes require certain CSS and JavaScript
 * includes.<p/>
 *
 * If, for example, your page has ajax components integrated, without having the default theme set to ajax, you might
 * want to use the head tag with <b>theme="ajax"</b> so that the typical ajax header setup will be included in the
 * page.<p/>
 *
 * The tag also includes the option to set a custom datepicker theme if needed. See calendarcss parameter for
 * description for details.<p/>
 *
 * If you use the ajax theme you can turn a debug flag on by setting the debug parameter to <tt>true</tt>.
 *
 * <!-- END SNIPPET: javadoc -->
 *
 * <p/> <b>Examples</b>
 *
 * <pre>
 * <!-- START SNIPPET: example1 -->
 * &lt;head&gt;
 *   &lt;title&gt;My page&lt;/title&gt;
 *   &lt;s:head/&gt;
 * &lt;/head&gt;
 * <!-- END SNIPPET: example1 -->
 * </pre>
 *
 * <pre>
 * <!-- START SNIPPET: example2 -->
 * &lt;head&gt;
 *   &lt;title&gt;My page&lt;/title&gt;
 *   &lt;s:head theme="ajax" calendarcss="calendar-green"/&gt;
 * &lt;/head&gt;
 * <!-- END SNIPPET: example2 -->
 * </pre>
 *
 * <pre>
 * <!-- START SNIPPET: example3 -->
 * &lt;head&gt;
 *   &lt;title&gt;My page&lt;/title&gt;
 *   &lt;s:head theme="ajax" debug="true"/&gt;
 * &lt;/head&gt;
 * <!-- END SNIPPET: example3 -->
 * </pre>
 *
 * @s.tag name="head" tld-body-content="empty" tld-tag-class="org.apache.struts2.views.jsp.ui.HeadTag"
 * description="Render a chunk of HEAD for your HTML file"
 */
public class Head extends UIBean {
    public static final String TEMPLATE = "head";

    private String calendarcss = "calendar-blue.css";
    private boolean debug;

    public Head(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    protected String getDefaultTemplate() {
        return TEMPLATE;
    }

    public void evaluateParams() {
        super.evaluateParams();

        if (calendarcss != null) {
            String css = findString(calendarcss);
            if (css != null && css.trim().length() > 0) {
                if (css.lastIndexOf(".css") < 0) {
                    addParameter("calendarcss", css + ".css");
                } else {
                    addParameter("calendarcss", css);
                }
            }
        }

        addParameter("encoding", Settings.get(StrutsConstants.STRUTS_I18N_ENCODING));
        addParameter("debug", Boolean.valueOf(debug).toString());
    }

    public String getCalendarcss() {
        return calendarcss;
    }

    /**
     * The jscalendar css theme to use" default="calendar-blue.css
     * @s.tagattribute required="false"
     */
    public void setCalendarcss(String calendarcss) {
        this.calendarcss = calendarcss;
    }

    public boolean isDebug() {
        return debug;
    }

    /**
     * Set to true to enable debugging mode for AJAX themes
     * @s.tagattribute required="false"
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }


}
