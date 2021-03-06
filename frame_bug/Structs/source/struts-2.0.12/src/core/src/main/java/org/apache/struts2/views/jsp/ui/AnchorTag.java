/*
 * $Id: AnchorTag.java 560385 2007-07-27 21:13:04Z musachy $
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
package org.apache.struts2.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Anchor;
import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * @see Anchor
 */
public class AnchorTag extends AbstractRemoteCallUITag {

	private static final long serialVersionUID = -1034616578492431113L;

    protected String targets;

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new Anchor(stack, req, res);
    }

    protected void populateParams() {
        super.populateParams();

        Anchor link = (Anchor) component;
        link.setTargets(targets);
    }

    public void setTargets(String targets) {
        this.targets = targets;
    }

    @Deprecated()
    public void setPreInvokeJS(String preInvokeJS) {
        this.beforeLoading = preInvokeJS;
    }
    
    @Deprecated()
    public void setOnLoadJS(String onLoadJS) {
        this.afterLoading = onLoadJS;
    }
}


