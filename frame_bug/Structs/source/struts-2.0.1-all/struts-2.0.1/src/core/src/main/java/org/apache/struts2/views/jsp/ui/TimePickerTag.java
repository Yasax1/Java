/*
 * $Id: TimePickerTag.java 451544 2006-09-30 05:38:02Z mrdon $
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.components.TimePicker;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * @version $Date: 2006-09-30 01:38:02 -0400 (Sat, 30 Sep 2006) $ $Id: TimePickerTag.java 451544 2006-09-30 05:38:02Z mrdon $
 */
public class TimePickerTag extends TextFieldTag {

	private static final long serialVersionUID = 3527737048468381376L;
	
	protected String format;
	protected String timeIconPath;
	protected String templatePath;
	protected String templateCssPath;

	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		return new TimePicker(stack, req, res);
	}
	
	protected void populateParams() {
		super.populateParams();
		
		final TimePicker timePicker = (TimePicker) component;
		timePicker.setFormat(format);
		timePicker.setTimeIconPath(timeIconPath);
		timePicker.setTemplatePath(templatePath);
		timePicker.setTemplateCssPath(templateCssPath);
	}
	
	public void setFormat(String format) {
        this.format = format;
    }
    
    public void setTimeIconPath(String timeIconPath) {
    	this.timeIconPath = timeIconPath;
    }
    
    public void setTemplatePath(String templatePath) {
    	this.templatePath = templatePath;
    }
    
    public void setTemplateCssPath(String templateCssPath) {
    	this.templateCssPath = templateCssPath;
    }
}
