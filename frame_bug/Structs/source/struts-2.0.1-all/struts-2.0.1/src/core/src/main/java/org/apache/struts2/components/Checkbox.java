/*
 * $Id: Checkbox.java 451544 2006-09-30 05:38:02Z mrdon $
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
 * <!-- START SNIPPET: javadoc -->
 * Renders an HTML input element of type checkbox, populated by the specified property from the ValueStack.
 * <!-- END SNIPPET: javadoc -->
 *
 * <p/> <b>Examples</b>
 *
 * <pre>
 * <!-- START SNIPPET: example -->
 * JSP:
 * &lt;s:checkbox label="checkbox test" name="checkboxField1" value="aBoolean" fieldValue="true"/&gt;
 *
 * Velocity:
 * #tag( Checkbox "label=checkbox test" "name=checkboxField1" "value=aBoolean" )
 *
 * Resulting HTML (simple template, aBoolean == true):
 * &lt;input type="checkbox" name="checkboxField1" value="true" checked="checked" /&gt;
 *
 * <!-- END SNIPPET: example -->
 * </pre>
 *
 * @s.tag name="checkbox" tld-body-content="JSP" tld-tag-class="org.apache.struts2.views.jsp.ui.CheckboxTag"
 * description="Render a checkbox input field"
  */
public class Checkbox extends UIBean {
    final public static String TEMPLATE = "checkbox";

    protected String fieldValue;

    public Checkbox(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    protected String getDefaultTemplate() {
        return TEMPLATE;
    }

    protected void evaluateExtraParams() {
        if (fieldValue != null) {
            addParameter("fieldValue", findString(fieldValue));
        } else {
            addParameter("fieldValue", "true");
        }
    }

    protected Class getValueClassType() {
        return Boolean.class; // for checkboxes, everything needs to end up as a Boolean
    }

    /**
     * The actual HTML value attribute of the checkbox.
     * @s.tagattribute required="false" default="'true'"
     */
    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

}
