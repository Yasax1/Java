/*
 * $Id: PanelTag.java 451544 2006-09-30 05:38:02Z mrdon $
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
import org.apache.struts2.components.Panel;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * @see Panel
 */
public class PanelTag extends DivTag {
	
	private static final long serialVersionUID = -1698805503599998611L;
	
	protected String tabName;
    protected String subscribeTopicName;
    protected String remote;

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new Panel(stack, req, res);
    }

    protected void populateParams() {
        super.populateParams();

        Panel panel = ((Panel) component);
        panel.setTabName(tabName);
        panel.setSubscribeTopicName(subscribeTopicName);
        panel.setRemote(remote);
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public void setSubscribeTopicName(String subscribeTopicName) {
        this.subscribeTopicName = subscribeTopicName;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }
}
