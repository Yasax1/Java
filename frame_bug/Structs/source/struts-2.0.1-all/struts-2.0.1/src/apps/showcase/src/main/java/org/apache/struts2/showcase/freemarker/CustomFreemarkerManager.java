/*
 * $Id: CustomFreemarkerManager.java 440597 2006-09-06 03:34:39Z wsmoak $
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
package org.apache.struts2.showcase.freemarker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.freemarker.FreemarkerManager;
import org.apache.struts2.views.freemarker.ScopesHashModel;

import com.opensymphony.xwork2.util.OgnlValueStack;

/**
 * This is an example of a custom FreemarkerManager, mean to be 
 * instantiated through Spring. 
 * <p/>
 * 
 * It will add into Freemarker's model
 * an utility class called {@link CustomFreemarkerManagerUtil} as a simple 
 * example demonstrating how to extends FreemarkerManager.
 * <p/>
 * 
 * The {@link CustomFreemarkerManagerUtil} will be created by Spring and 
 * injected through constructor injection.
 * <p/>
 */
public class CustomFreemarkerManager extends FreemarkerManager {
	
	private CustomFreemarkerManagerUtil util;
	
	public CustomFreemarkerManager(CustomFreemarkerManagerUtil util) {
		this.util = util;
	}
	
	public void populateContext(ScopesHashModel model, OgnlValueStack stack, Object action, HttpServletRequest request, HttpServletResponse response) {
		super.populateContext(model, stack, action, request, response);
		model.put("customFreemarkerManagerUtil", util);
	}
}
