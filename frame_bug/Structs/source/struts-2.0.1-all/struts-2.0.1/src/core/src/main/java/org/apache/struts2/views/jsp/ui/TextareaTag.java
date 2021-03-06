/*
 * $Id: TextareaTag.java 451544 2006-09-30 05:38:02Z mrdon $
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
import org.apache.struts2.components.TextArea;

import com.opensymphony.xwork2.util.ValueStack;


/**
 * @see TextArea
 */
public class TextareaTag extends AbstractUITag {
	
	private static final long serialVersionUID = -4107122506712927927L;
	
	protected String cols;
    protected String readonly;
    protected String rows;
    protected String wrap;

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new TextArea(stack, req, res);
    }

    protected void populateParams() {
        super.populateParams();

        TextArea textArea = ((TextArea) component);
        textArea.setCols(cols);
        textArea.setReadonly(readonly);
        textArea.setRows(rows);
        textArea.setWrap(wrap);
    }

    public void setCols(String cols) {
        this.cols = cols;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public void setWrap(String wrap) {
        this.wrap = wrap;
    }

}
