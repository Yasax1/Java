/*
 * $Id: ElseIfTagTest.java 454565 2006-10-10 00:02:56Z jmitchell $
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

import javax.servlet.jsp.tagext.TagSupport;

import junit.framework.TestCase;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.If;

import com.mockobjects.servlet.MockJspWriter;
import com.mockobjects.servlet.MockPageContext;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.ValueStackFactory;

/**
 * 
 */
public class ElseIfTagTest extends TestCase {

	protected MockPageContext pageContext;
	protected MockJspWriter jspWriter;
	protected ValueStack stack;
	
	
	public void testIfIsFalseElseIfIsTrue() throws Exception {
		stack.getContext().put(If.ANSWER, Boolean.FALSE);
		
		ElseIfTag tag = new ElseIfTag();
		tag.setPageContext(pageContext);
		tag.setTest("true");
		
		int result = tag.doStartTag();
		tag.doEndTag();
		
		assertEquals(result, TagSupport.EVAL_BODY_INCLUDE);
	}
	
	public void testIfIsFalseElseIfIsFalse() throws Exception {
		stack.getContext().put(If.ANSWER, Boolean.FALSE);
		
		ElseIfTag tag = new ElseIfTag();
		tag.setPageContext(pageContext);
		tag.setTest("false");
		
		int result = tag.doStartTag();
		tag.doEndTag();
		
		assertEquals(result, TagSupport.SKIP_BODY);
	}
	
	public void testIfIsTrueElseIfIsTrue() throws Exception {
		stack.getContext().put(If.ANSWER, Boolean.TRUE);
		
		ElseIfTag tag = new ElseIfTag();
		tag.setPageContext(pageContext);
		tag.setTest("true");
		
		int result = tag.doStartTag();
		tag.doEndTag();
		
		assertEquals(result, TagSupport.SKIP_BODY);
	}
	
	public void testIfIsTrueElseIfIsFalse() throws Exception {
		stack.getContext().put(If.ANSWER, Boolean.TRUE);
		
		ElseIfTag tag = new ElseIfTag();
		tag.setPageContext(pageContext);
		tag.setTest("false");
		
		int result = tag.doStartTag();
		tag.doEndTag();
		
		assertEquals(result, TagSupport.SKIP_BODY);
	}
	
	
	protected void setUp() throws Exception {
		stack = ValueStackFactory.getFactory().createValueStack();
		
		jspWriter = new MockJspWriter();
		
		StrutsMockHttpServletRequest request = new StrutsMockHttpServletRequest();
		
		StrutsMockServletContext servletContext = new StrutsMockServletContext();
		servletContext.setServletInfo("not-weblogic");
		
		pageContext = new MockPageContext();
		pageContext.setJspWriter(jspWriter);
		pageContext.setRequest(request);
		pageContext.setServletContext(servletContext);
		
		request.setAttribute(ServletActionContext.STRUTS_VALUESTACK_KEY, stack);
	}
	
	
}
