/*
 * $Id: FieldErrorTagTest.java 439747 2006-09-03 09:22:46Z mrdon $
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.views.jsp.AbstractUITagTest;
import org.apache.struts2.views.jsp.ParamTag;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * FieldError Tag Test Case.
 * 
 */
public class FieldErrorTagTest extends AbstractUITagTest {

	
	public void testWithoutParamsWithFieldErrors() throws Exception {
		FieldErrorTag tag = new FieldErrorTag();
		((InternalAction)action).setHaveFieldErrors(true);
		tag.setPageContext(pageContext);
		tag.doStartTag();
		tag.doEndTag();
		
		verify(FieldErrorTagTest.class.getResource("fielderror-1.txt"));
	}
	
	public void testWithoutParamsWithoutFieldErrors() throws Exception {
		FieldErrorTag tag = new FieldErrorTag();
		((InternalAction)action).setHaveFieldErrors(false);
		tag.setPageContext(pageContext);
		tag.doStartTag();
		tag.doEndTag();
		
		verify(FieldErrorTagTest.class.getResource("fielderror-2.txt"));
	}
	
	public void testWithParamsWithFieldErrors1() throws Exception {
		FieldErrorTag tag = new FieldErrorTag();
		((InternalAction)action).setHaveFieldErrors(true);
		tag.setPageContext(pageContext);
		tag.doStartTag();
			ParamTag pTag1 = new ParamTag();
			pTag1.setPageContext(pageContext);
			pTag1.setValue("%{'field1'}");
			pTag1.doStartTag();
			pTag1.doEndTag();
			
			ParamTag pTag2 = new ParamTag();
			pTag2.setPageContext(pageContext);
			pTag2.setValue("%{'field3'}");
			pTag2.doStartTag();
			pTag2.doEndTag();
			
		tag.doEndTag();
		
		verify(FieldErrorTagTest.class.getResource("fielderror-3.txt"));
	}
	
	public void testWithParamsWithFieldErrors2() throws Exception {
		FieldErrorTag tag = new FieldErrorTag();
		((InternalAction)action).setHaveFieldErrors(true);
		tag.setPageContext(pageContext);
		tag.doStartTag();
			ParamTag pTag1 = new ParamTag();
			pTag1.setPageContext(pageContext);
			pTag1.setValue("%{'field1'}");
			pTag1.doStartTag();
			pTag1.doEndTag();
			
			ParamTag pTag2 = new ParamTag();
			pTag2.setPageContext(pageContext);
			pTag2.setValue("%{'field2'}");
			pTag2.doStartTag();
			pTag2.doEndTag();
			
		tag.doEndTag();
		
		verify(FieldErrorTagTest.class.getResource("fielderror-4.txt"));
	}
	
	
	public void testWithParamsWithFieldErrors3() throws Exception {
		FieldErrorTag tag = new FieldErrorTag();
		((InternalAction)action).setHaveFieldErrors(true);
		tag.setPageContext(pageContext);
		tag.doStartTag();
			ParamTag pTag1 = new ParamTag();
			pTag1.setPageContext(pageContext);
			pTag1.setValue("%{'field2'}");
			pTag1.doStartTag();
			pTag1.doEndTag();
			
		tag.doEndTag();
		
		verify(FieldErrorTagTest.class.getResource("fielderror-5.txt"));
	}
	
	public void testWithParamsWithoutFieldErrors1() throws Exception {
		FieldErrorTag tag = new FieldErrorTag();
		((InternalAction)action).setHaveFieldErrors(false);
		tag.setPageContext(pageContext);
		tag.doStartTag();
			ParamTag pTag1 = new ParamTag();
			pTag1.setPageContext(pageContext);
			pTag1.setValue("%{'field1'}");
			pTag1.doStartTag();
			pTag1.doEndTag();
			
			ParamTag pTag2 = new ParamTag();
			pTag2.setPageContext(pageContext);
			pTag2.setValue("%{'field3'}");
			pTag2.doStartTag();
			pTag2.doEndTag();
		tag.doEndTag();
		
		verify(FieldErrorTagTest.class.getResource("fielderror-2.txt"));
	}
	
	public void testWithParamsWithoutFieldErrors2() throws Exception {
		FieldErrorTag tag = new FieldErrorTag();
		((InternalAction)action).setHaveFieldErrors(false);
		tag.setPageContext(pageContext);
		tag.doStartTag();
			ParamTag pTag1 = new ParamTag();
			pTag1.setPageContext(pageContext);
			pTag1.setValue("%{'field1'}");
			pTag1.doStartTag();
			pTag1.doEndTag();
			
			ParamTag pTag2 = new ParamTag();
			pTag2.setPageContext(pageContext);
			pTag2.setValue("%{'field3'}");
			pTag2.doStartTag();
			pTag2.doEndTag();
		tag.doEndTag();
		
		verify(FieldErrorTagTest.class.getResource("fielderror-2.txt"));
	}
	
	
	public void testWithParamsWithoutFieldErrors3() throws Exception {
		FieldErrorTag tag = new FieldErrorTag();
		((InternalAction)action).setHaveFieldErrors(false);
		tag.setPageContext(pageContext);
		tag.doStartTag();
			ParamTag pTag1 = new ParamTag();
			pTag1.setPageContext(pageContext);
			pTag1.setValue("%{'field2'}");
			pTag1.doStartTag();
			pTag1.doEndTag();
			
		tag.doEndTag();
		
		verify(FieldErrorTagTest.class.getResource("fielderror-2.txt"));
	}
	
	public void testWithNullFieldErrors() throws Exception {
		FieldErrorTag tag = new FieldErrorTag();
		((InternalAction)action).setHaveFieldErrors(false);
		((InternalAction)action).setReturnNullForFieldErrors(true);
		tag.setPageContext(pageContext);
		tag.doStartTag();
			ParamTag pTag1 = new ParamTag();
			pTag1.setPageContext(pageContext);
			pTag1.setValue("%{'field2'}");
			pTag1.doStartTag();
			pTag1.doEndTag();
			
		tag.doEndTag();
		
		verify(FieldErrorTagTest.class.getResource("fielderror-2.txt"));
	}
	
	
	public Action getAction() {
		return new InternalAction();
	}
	
	
	public class InternalAction extends ActionSupport {
		
		private boolean haveFieldErrors = false;
		private boolean returnNullForFieldErrors = false;
		
		public void setHaveFieldErrors(boolean haveFieldErrors) {
			this.haveFieldErrors = haveFieldErrors;
		}
		
		public void setReturnNullForFieldErrors(boolean returnNullForFieldErrors) {
			this.returnNullForFieldErrors = returnNullForFieldErrors;
		}
		
		public Map getFieldErrors() {
			if (haveFieldErrors) {
				List err1 = new ArrayList();
				err1.add("field error message number 1");
				List err2 = new ArrayList();
				err2.add("field error message number 2");
				List err3 = new ArrayList();
				err3.add("field error message number 3");
				Map fieldErrors = new LinkedHashMap();
				fieldErrors.put("field1", err1);
				fieldErrors.put("field2", err2);
				fieldErrors.put("field3", err3);
				return fieldErrors;
			}
			else if (returnNullForFieldErrors) {
				return null;
			}
			else {
				return Collections.EMPTY_MAP;
			}
		}
		
		public boolean hasFieldErrors() {
			return haveFieldErrors;
		}
	}
}

