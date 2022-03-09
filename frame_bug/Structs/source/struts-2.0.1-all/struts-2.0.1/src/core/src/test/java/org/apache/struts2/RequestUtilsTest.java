/*
 * $Id: RequestUtilsTest.java 439747 2006-09-03 09:22:46Z mrdon $
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
package org.apache.struts2;

/**
 * <code>RequestUtilsTest</code>
 *
 */
import javax.servlet.http.HttpServletRequest;

import junit.framework.TestCase;

import org.easymock.MockControl;

public class RequestUtilsTest extends TestCase {

    private MockControl control;
    private HttpServletRequest requestMock;

    public void testGetServletPathWithServletPathSet() throws Exception {
        control.expectAndReturn(requestMock.getServletPath(), "/mycontext/");
        control.replay();
        assertEquals("/mycontext/", RequestUtils.getServletPath(requestMock));
        control.verify();
    }

    public void testGetServletPathWithRequestURIAndEmptyContextPath() throws Exception {
        control.expectAndReturn(requestMock.getServletPath(), null);
        control.expectAndReturn(requestMock.getRequestURI(), "/mycontext/test.jsp");
        control.expectAndReturn(requestMock.getContextPath(), "");
        control.expectAndReturn(requestMock.getPathInfo(), "test.jsp");
        control.expectAndReturn(requestMock.getPathInfo(), "test.jsp");
        control.replay();
        assertEquals("/mycontext/", RequestUtils.getServletPath(requestMock));
        control.verify();
    }

    public void testGetServletPathWithRequestURIAndContextPathSet() throws Exception {
        control.expectAndReturn(requestMock.getServletPath(), null);
        control.expectAndReturn(requestMock.getRequestURI(), "/servlet/mycontext/test.jsp");
        control.expectAndReturn(requestMock.getContextPath(), "/servlet");
        control.expectAndReturn(requestMock.getContextPath(), "/servlet");
        control.expectAndReturn(requestMock.getPathInfo(), "test.jsp");
        control.expectAndReturn(requestMock.getPathInfo(), "test.jsp");
        control.replay();
        assertEquals("/mycontext/", RequestUtils.getServletPath(requestMock));
        control.verify();
    }

    public void testGetServletPathWithRequestURIAndContextPathSetButNoPatchInfo() throws Exception {
        control.expectAndReturn(requestMock.getServletPath(), null);
        control.expectAndReturn(requestMock.getRequestURI(), "/servlet/mycontext/");
        control.expectAndReturn(requestMock.getContextPath(), "/servlet");
        control.expectAndReturn(requestMock.getContextPath(), "/servlet");
        control.expectAndReturn(requestMock.getPathInfo(), null);
        control.replay();
        assertEquals("/mycontext/", RequestUtils.getServletPath(requestMock));
        control.verify();
    }

    protected void setUp() {
        control = MockControl.createControl(HttpServletRequest.class);
        requestMock = (HttpServletRequest) control.getMock();
    }

}
