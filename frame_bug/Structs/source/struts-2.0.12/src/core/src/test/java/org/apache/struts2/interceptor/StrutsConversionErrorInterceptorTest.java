/*
 * $Id: StrutsConversionErrorInterceptorTest.java 471756 2006-11-06 15:01:43Z husted $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.struts2.interceptor;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.StrutsTestCase;

import com.mockobjects.dynamic.C;
import com.mockobjects.dynamic.Mock;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.ValueStackFactory;


/**
 * StrutsConversionErrorInterceptorTest
 *
 */
public class StrutsConversionErrorInterceptorTest extends StrutsTestCase {

    protected ActionContext context;
    protected ActionInvocation invocation;
    protected Map conversionErrors;
    protected Mock mockInvocation;
    protected ValueStack stack;
    protected StrutsConversionErrorInterceptor interceptor;


    public void testEmptyValuesDoNotSetFieldErrors() throws Exception {
        conversionErrors.put("foo", new Long(123));
        conversionErrors.put("bar", "");
        conversionErrors.put("baz", new String[]{""});

        ActionSupport action = new ActionSupport();
        mockInvocation.expectAndReturn("getAction", action);
        stack.push(action);
        mockInvocation.matchAndReturn("getAction",action);
        assertNull(action.getFieldErrors().get("foo"));
        assertNull(action.getFieldErrors().get("bar"));
        assertNull(action.getFieldErrors().get("baz"));
        interceptor.intercept(invocation);
        assertTrue(action.hasFieldErrors());
        assertNotNull(action.getFieldErrors().get("foo"));
        assertNull(action.getFieldErrors().get("bar"));
        assertNull(action.getFieldErrors().get("baz"));
    }

    public void testFieldErrorAdded() throws Exception {
        conversionErrors.put("foo", new Long(123));

        ActionSupport action = new ActionSupport();
        mockInvocation.expectAndReturn("getAction", action);
        stack.push(action);
        mockInvocation.matchAndReturn("getAction",action);
        assertNull(action.getFieldErrors().get("foo"));
        interceptor.intercept(invocation);
        assertTrue(action.hasFieldErrors());
        assertNotNull(action.getFieldErrors().get("foo"));
    }

    protected void setUp() throws Exception {
        super.setUp();
        interceptor = new StrutsConversionErrorInterceptor();
        mockInvocation = new Mock(ActionInvocation.class);
        invocation = (ActionInvocation) mockInvocation.proxy();
        stack = ValueStackFactory.getFactory().createValueStack();
        context = new ActionContext(stack.getContext());
        conversionErrors = new HashMap();
        context.setConversionErrors(conversionErrors);
        mockInvocation.matchAndReturn("getInvocationContext", context);
        mockInvocation.expectAndReturn("invoke", Action.SUCCESS);
        mockInvocation.expectAndReturn("getStack", stack);
        mockInvocation.expect("addPreResultListener", C.ANY_ARGS);
    }
}
