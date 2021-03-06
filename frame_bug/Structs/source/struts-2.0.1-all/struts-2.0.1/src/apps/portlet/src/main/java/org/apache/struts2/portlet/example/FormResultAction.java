/*
 * $Id: FormResultAction.java 420385 2006-07-10 00:57:05Z mrdon $
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
package org.apache.struts2.portlet.example;

import java.util.Collection;
import java.util.Map;

import javax.portlet.RenderRequest;

import org.apache.struts2.portlet.context.PortletActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 */
public class FormResultAction extends ActionSupport {

    private String result = null;
    
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    
    public Collection getRenderParams() {
        RenderRequest req = PortletActionContext.getRenderRequest();
        Map params = req.getParameterMap();
        return params.entrySet();
    }
}
