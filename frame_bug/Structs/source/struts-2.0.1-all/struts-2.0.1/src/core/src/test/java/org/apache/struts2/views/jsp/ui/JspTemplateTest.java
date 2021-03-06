/*
 * $Id: JspTemplateTest.java 439747 2006-09-03 09:22:46Z mrdon $
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
package org.apache.struts2.views.jsp.ui;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.TestAction;
import org.apache.struts2.views.jsp.AbstractUITagTest;

import com.mockobjects.dynamic.C;
import com.mockobjects.dynamic.Mock;

/**
 * JspTemplateTest
 *
 */
public class JspTemplateTest extends AbstractUITagTest {
    public void testCheckBox() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("true");

        CheckboxTag tag = new CheckboxTag();
        Mock rdMock = new Mock(RequestDispatcher.class);
        rdMock.expect("include",C.args(C.isA(HttpServletRequest.class), C.isA(HttpServletResponse.class)));
        RequestDispatcher dispatcher = (RequestDispatcher) rdMock.proxy();
        request.setupGetRequestDispatcher(dispatcher);
        tag.setPageContext(pageContext);
        tag.setTemplate("/test/checkbox.jsp");
        tag.doStartTag();
        tag.doEndTag();
        rdMock.verify();
    }
}
