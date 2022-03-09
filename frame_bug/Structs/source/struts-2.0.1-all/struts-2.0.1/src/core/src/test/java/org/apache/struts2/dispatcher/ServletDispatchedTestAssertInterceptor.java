/*
 * $Id: ServletDispatchedTestAssertInterceptor.java 439747 2006-09-03 09:22:46Z mrdon $
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
package org.apache.struts2.dispatcher;

import junit.framework.Assert;

import org.apache.struts2.TestAction;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


/**
 */
public class ServletDispatchedTestAssertInterceptor implements Interceptor {
	
	private static final long serialVersionUID = 1980347231443329805L;

	public ServletDispatchedTestAssertInterceptor() {
        super();
    }

    public void destroy() {
    }

    public void init() {
    }

    public String intercept(ActionInvocation invocation) throws Exception {
        Assert.assertTrue(invocation.getAction() instanceof TestAction);

        TestAction testAction = (TestAction) invocation.getAction();

        Assert.assertEquals("bar", testAction.getFoo());

        String result = invocation.invoke();

        return result;
    }
}
