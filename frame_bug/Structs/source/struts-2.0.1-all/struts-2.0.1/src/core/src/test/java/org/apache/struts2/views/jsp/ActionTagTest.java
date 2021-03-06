/*
 * $Id: ActionTagTest.java 454565 2006-10-10 00:02:56Z jmitchell $
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
package org.apache.struts2.views.jsp;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsException;
import org.apache.struts2.TestAction;
import org.apache.struts2.TestActionTagResult;
import org.apache.struts2.TestConfigurationProvider;
import org.apache.struts2.components.ActionComponent;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.ValueStackFactory;


/**
 * Unit test for {@link ActionTag}.
 */
public class ActionTagTest extends AbstractTagTest {

    public void testActionTagWithNamespace() {
        request.setupGetServletPath(TestConfigurationProvider.TEST_NAMESPACE + "/" + "foo.action");

        ActionTag tag = new ActionTag();
        tag.setPageContext(pageContext);
        tag.setName(TestConfigurationProvider.TEST_NAMESPACE_ACTION);
        tag.setId(TestConfigurationProvider.TEST_NAMESPACE_ACTION);

        try {
            tag.doStartTag();
            ActionComponent ac = ((ActionComponent) tag.component);
            tag.doEndTag();
            ActionProxy proxy = ac.getProxy();

            Object o = pageContext.findAttribute(TestConfigurationProvider.TEST_NAMESPACE_ACTION);
            assertTrue(o instanceof TestAction);

            assertEquals(TestConfigurationProvider.TEST_NAMESPACE, proxy.getNamespace());
        } catch (JspException ex) {
            ex.printStackTrace();
            fail();
        }
    }

    public void testSimple() {
        request.setupGetServletPath("/foo.action");

        ActionTag tag = new ActionTag();
        tag.setPageContext(pageContext);
        tag.setName("testAction");
        tag.setId("testAction");

        int stackSize = stack.size();

        try {
            tag.doStartTag();
            tag.addParameter("foo", "myFoo");
            tag.doEndTag();

            assertEquals(stack.size(), ActionContext.getContext().getValueStack().size());
            assertEquals("myFoo", stack.findValue("#testAction.foo"));
            assertEquals(stackSize, stack.size());

            Object o = pageContext.findAttribute("testAction");
            assertTrue(o instanceof TestAction);
            assertEquals("myFoo", ((TestAction) o).getFoo());
            assertEquals(Action.SUCCESS, ((TestAction) o).getResult());
        } catch (JspException ex) {
            ex.printStackTrace();
            fail();
        }
    }

    public void testSimpleWithoutServletActionContext() {
        ServletActionContext.setRequest(null);
        ServletActionContext.setResponse(null);
        this.testSimple();
    }

    public void testActionWithExecuteResult() throws Exception {
        ActionTag tag = new ActionTag();
        tag.setPageContext(pageContext);
        tag.setNamespace("");
        tag.setName("testActionTagAction");
        tag.setExecuteResult(true);

        tag.doStartTag();

        // tag clear components on doEndTag
        ActionComponent component = (ActionComponent) tag.getComponent();

        tag.doEndTag();

        TestActionTagResult result = (TestActionTagResult) component.getProxy().getInvocation().getResult();

        assertTrue(stack.getContext().containsKey(ServletActionContext.PAGE_CONTEXT));
        assertTrue(stack.getContext().get(ServletActionContext.PAGE_CONTEXT)instanceof PageContext);
        assertTrue(result.isExecuted());
    }

    public void testActionWithoutExecuteResult() throws Exception {
        ActionTag tag = new ActionTag();
        tag.setPageContext(pageContext);
        tag.setNamespace("");
        tag.setName("testActionTagAction");
        tag.setExecuteResult(false);

        tag.doStartTag();

        // tag clear components on doEndTag, so we need to get it here
        ActionComponent component = (ActionComponent) tag.getComponent();

        tag.doEndTag();

        TestActionTagResult result = (TestActionTagResult) component.getProxy().getInvocation().getResult();

        assertTrue(stack.getContext().containsKey(ServletActionContext.PAGE_CONTEXT));
        assertTrue(stack.getContext().get(ServletActionContext.PAGE_CONTEXT)instanceof PageContext);
        assertNull(result); // result is never executed, hence never set into invocation
    }

    public void testIngoreContextParamsFalse() throws Exception {
        ActionTag tag = new ActionTag();
        tag.setPageContext(pageContext);
        tag.setNamespace("");
        tag.setName("testActionTagAction");
        tag.setExecuteResult(false);
        tag.setIgnoreContextParams(false);
        ActionContext.getContext().getParameters().put("user", "Santa Claus");

        tag.doStartTag();

        // tag clear components on doEndTag, so we need to get it here
        ActionComponent component = (ActionComponent) tag.getComponent();

        tag.doEndTag();

        // check parameters, there should be one
        ActionInvocation ai = component.getProxy().getInvocation();
        ActionContext ac = ai.getInvocationContext();
        assertEquals(1, ac.getParameters().size());
    }

    public void testIngoreContextParamsTrue() throws Exception {
        ActionTag tag = new ActionTag();
        tag.setPageContext(pageContext);
        tag.setNamespace("");
        tag.setName("testActionTagAction");
        tag.setExecuteResult(false);
        tag.setIgnoreContextParams(true);
        ActionContext.getContext().getParameters().put("user", "Santa Claus");

        tag.doStartTag();

        // tag clear components on doEndTag, so we need to get it here
        ActionComponent component = (ActionComponent) tag.getComponent();

        tag.doEndTag();

        // check parameters, there should be one
        ActionInvocation ai = component.getProxy().getInvocation();
        ActionContext ac = ai.getInvocationContext();
        assertEquals(0, ac.getParameters().size());
    }

    public void testNoNameDefined() throws Exception {
        ActionTag tag = new ActionTag();
        tag.setPageContext(pageContext);
        tag.setNamespace("");
        tag.setName(null);
        tag.setExecuteResult(false);

        try {
            tag.doStartTag();
            tag.doEndTag();
            fail("Should have thrown RuntimeException");
        } catch (StrutsException e) {
             assertEquals("tag 'actioncomponent', field 'name': Action name is required. Example: updatePerson", e.getMessage());
        }
    }

    // FIXME: Logging the error seems to cause the standard Maven build to fail
    public void testUnknownNameDefined() throws Exception {
        ActionTag tag = new ActionTag();
        tag.setPageContext(pageContext);
        tag.setNamespace("");
        tag.setName("UNKNOWN_NAME");
        tag.setExecuteResult(false);

        tag.doStartTag();
        tag.doEndTag();
        // will just log it to ERROR but we run the code to test that it works somehow
    }

    public void testActionMethodWithExecuteResult() throws Exception {
        ActionTag tag = new ActionTag();
        tag.setPageContext(pageContext);
        tag.setNamespace("");
        tag.setName("testActionTagAction!input");
        tag.setExecuteResult(true);

        tag.doStartTag();

        // tag clear components on doEndTag
        ActionComponent component = (ActionComponent) tag.getComponent();

        tag.doEndTag();

        TestActionTagResult result = (TestActionTagResult) component.getProxy().getInvocation().getResult();

        assertTrue(stack.getContext().containsKey(ServletActionContext.PAGE_CONTEXT));
        assertTrue(stack.getContext().get(ServletActionContext.PAGE_CONTEXT)instanceof PageContext);
        assertTrue(result.isExecuted());
    }

    protected void setUp() throws Exception {
        super.setUp();

        configurationManager.clearConfigurationProviders();
        configurationManager.addConfigurationProvider(new TestConfigurationProvider());
        configurationManager.reload();

        ActionContext actionContext = new ActionContext(context);
        actionContext.setValueStack(stack);
        ActionContext.setContext(actionContext);
    }

    protected void tearDown() throws Exception {
        configurationManager.destroyConfiguration();

        ValueStack stack = ValueStackFactory.getFactory().createValueStack();
        ActionContext.setContext(new ActionContext(stack.getContext()));
        super.tearDown();
    }
}
