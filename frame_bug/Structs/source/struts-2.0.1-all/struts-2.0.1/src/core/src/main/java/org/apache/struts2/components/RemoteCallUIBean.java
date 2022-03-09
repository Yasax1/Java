/*
 * $Id: RemoteCallUIBean.java 451544 2006-09-30 05:38:02Z mrdon $
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

import com.opensymphony.xwork2.util.ValueStack;

/**
 * RemoteCallUIBean is superclass for all components dealing with remote calls.
 *
 */
public abstract class RemoteCallUIBean extends ClosingUIBean {

    protected String href;
    protected String errorText;
    protected String showErrorTransportText;
    protected String afterLoading;

    public RemoteCallUIBean(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    public void evaluateExtraParams() {
        super.evaluateExtraParams();

        if (href != null) {
            addParameter("href", findString(href));
        }

        if (showErrorTransportText != null) {
            addParameter("showErrorTransportText", findValue(showErrorTransportText, Boolean.class));
        }

        if (errorText != null) {
            addParameter("errorText", findString(errorText));
        }

        if (afterLoading != null) {
            addParameter("afterLoading", findString(afterLoading));
        }
    }

    /**
     * The theme to use for the element. <b>This tag will usually use the ajax theme.</b>
     * @s.tagattribute required="false" type="String"
     */
    public void setTheme(String theme) {
        super.setTheme(theme);
    }

    /**
     * The URL to call to obtain the content
     * @s.tagattribute required="false" type="String"
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * The text to display to the user if the is an error fetching the content
     * @s.tagattribute required="false" type="String"
     */
    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    /**
     * when to show the error message as content when the URL had problems
     * @s.tagattribute required="false" type="Boolean" default="false"
     */
    public void setShowErrorTransportText(String showErrorTransportText) {
        this.showErrorTransportText = showErrorTransportText;
    }

    /**
     * Javascript code that will be executed after the content has been fetched
     * @s.tagattribute required="false" type="String"
     */
    public void setAfterLoading(String afterLoading) {
        this.afterLoading = afterLoading;
    }
}
