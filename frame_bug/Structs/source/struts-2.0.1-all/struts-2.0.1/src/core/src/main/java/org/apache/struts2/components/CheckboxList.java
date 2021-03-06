/*
 * $Id: CheckboxList.java 451544 2006-09-30 05:38:02Z mrdon $
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
 *
 * Creates a series of checkboxes from a list. Setup is like &lt;s:select /&gt; or &lt;s:radio /&gt;, but creates checkbox tags.
 *
 * <!-- END SNIPPET: javadoc -->
 *
 * <p/> <b>Examples</b>
 *
 * <pre>
 * <!-- START SNIPPET: example -->
 * &lt;s:checkboxlist name="foo" list="bar"/&gt;
 * <!-- END SNIPPET: example -->
 * </pre>
 *
 * @s.tag name="checkboxlist" tld-body-content="JSP" tld-tag-class="org.apache.struts2.views.jsp.ui.CheckboxListTag"
 * description="Render a list of checkboxes"
  */
public class CheckboxList extends ListUIBean {
    final public static String TEMPLATE = "checkboxlist";

    public CheckboxList(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    protected String getDefaultTemplate() {
        return TEMPLATE;
    }
}
