/*
 * $Id: Example4ShowPanelAction.java 440597 2006-09-06 03:34:39Z wsmoak $
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
package org.apache.struts2.showcase.ajax;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @version $Date: 2006-09-05 23:34:39 -0400 (Tue, 05 Sep 2006) $ $Id: Example4ShowPanelAction.java 440597 2006-09-06 03:34:39Z wsmoak $
 */
public class Example4ShowPanelAction extends ActionSupport {

	private String name;
	private String gender;
	
	private static final long serialVersionUID = 7751976335066456596L;

	public String panel1() throws Exception {
		return SUCCESS;
	}
	
	public String panel2() throws Exception {
		return SUCCESS;
	}
	
	public String panel3() throws Exception {
		return SUCCESS;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTodayDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
		return sdf.format(new Date());
	}
	
	public String getTodayTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("kk:mm:ss");
		return sdf.format(new Date());
	}
}
