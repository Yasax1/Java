/*
 * $Id: ElseTagTest.java 454565 2006-10-10 00:02:56Z jmitchell $
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
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsTestCase;
import org.apache.struts2.components.If;

import com.mockobjects.servlet.MockJspWriter;
import com.mockobjects.servlet.MockPageContext;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.ValueStackFactory;


/**
 */
public class ElseTagTest extends StrutsTestCase {

    ElseTag elseTag;
    MockPageContext pageContext;
    ValueStack stack;


    public void testTestFalse() {
    	stack.getContext().put(If.ANSWER, new Boolean(false));

        int result = 0;

        try {
        	elseTag.setPageContext(pageContext);
            result = elseTag.doStartTag();
            elseTag.doEndTag();
        } catch (JspException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(TagSupport.EVAL_BODY_INCLUDE, result);
    }

    public void testTestNull() {
        elseTag.setPageContext(pageContext);

        int result = 0;

        try {
            result = elseTag.doStartTag();
        } catch (JspException e) {
            e.printStackTrace();
            fail();
        }

        assertEquals(TagSupport.SKIP_BODY, result);
    }

    public void testTestTrue() {
    	stack.getContext().put(If.ANSWER, new Boolean(true));
        elseTag.setPageContext(pageContext);

        int result = 0;

        try {
            result = elseTag.doStartTag();
        } catch (JspException e) {
            e.printStackTrace();
            fail();
        }

        assertEquals(TagSupport.SKIP_BODY, result);
    }

    protected void setUp() throws Exception {
        // create the needed objects
        elseTag = new ElseTag();
        stack = ValueStackFactory.getFactory().createValueStack();

        // create the mock http servlet request
        StrutsMockHttpServletRequest request = new StrutsMockHttpServletRequest();
        
        // NOTE: in Struts Tag library, TagUtil gets stack from request, which will be set
        //       when request going through the FilterDispatcher --> DispatcherUtil etc. route
        request.setAttribute(ServletActionContext.STRUTS_VALUESTACK_KEY, stack);

        StrutsMockServletContext servletContext = new StrutsMockServletContext();
        servletContext.setServletInfo("not-weblogic");
        
        // create the mock page context
        pageContext = new StrutsMockPageContext();
        pageContext.setRequest(request);
        pageContext.setServletContext(servletContext);
        pageContext.setJspWriter(new MockJspWriter());
    }


    class Foo {
        int num;

        public void setNum(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }
    }
}
