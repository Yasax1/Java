/*
 * $Id: ModelDrivenAction.java 432886 2006-08-19 22:03:04Z husted $
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
package org.apache.struts2.showcase.modelDriven;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Action to demonstrate simple model-driven feature of the framework.
 * 
 */
public class ModelDrivenAction extends ActionSupport implements ModelDriven {

	private static final long serialVersionUID = 1271130427666936592L;

	public String input() throws Exception {
		return SUCCESS;
	}
	
	public String execute() throws Exception {
		return SUCCESS;
	}

	public Object getModel() {
		return new Gangster();
	}
}
