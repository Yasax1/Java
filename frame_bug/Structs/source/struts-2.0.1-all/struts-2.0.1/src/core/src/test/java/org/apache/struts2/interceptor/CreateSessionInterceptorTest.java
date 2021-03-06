/*
 * $Id: CreateSessionInterceptorTest.java 439747 2006-09-03 09:22:46Z mrdon $
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

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsTestCase;
import org.jmock.Mock;
import org.jmock.core.constraint.IsEqual;
import org.jmock.core.matcher.InvokeOnceMatcher;

import com.opensymphony.xwork2.mock.MockActionInvocation;

/**
 * Test case for CreateSessionInterceptor.
 * 
 */
public class CreateSessionInterceptorTest extends StrutsTestCase {

	public void testCreateSession() throws Exception {
		Mock httpServletRequestMock = new Mock(HttpServletRequest.class);
		httpServletRequestMock.expects(new InvokeOnceMatcher()).method("getSession").with(new IsEqual(Boolean.TRUE));
		HttpServletRequest request = (HttpServletRequest) httpServletRequestMock.proxy();

		ServletActionContext.setRequest(request);

		CreateSessionInterceptor interceptor = new CreateSessionInterceptor();
		interceptor.intercept(new MockActionInvocation());

		httpServletRequestMock.verify();
	}
}
