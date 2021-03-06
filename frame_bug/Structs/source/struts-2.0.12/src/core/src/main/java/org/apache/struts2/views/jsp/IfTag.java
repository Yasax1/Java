/*
 * $Id: IfTag.java 471756 2006-11-06 15:01:43Z husted $
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
package org.apache.struts2.views.jsp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.components.If;

import com.opensymphony.xwork2.util.ValueStack;


/**
 * @see If
 */
public class IfTag extends ComponentTagSupport {

    private static final long serialVersionUID = 4448870162549923833L;

    String test;

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new If(stack);
    }

    protected void populateParams() {
        ((If) getComponent()).setTest(test);
    }

    public void setTest(String test) {
        this.test = test;
    }
}
