/*
 * $Id: StrutsTestCaseHelper.java 502294 2007-02-01 17:28:00Z niallp $
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
package org.apache.struts2.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.dispatcher.Dispatcher;
import org.springframework.mock.web.MockServletContext;

import com.opensymphony.xwork2.util.LocalizedTextUtil;

/**
 * Generic test setup methods to be used with any unit testing framework. 
 */
public class StrutsTestCaseHelper {
    
    /**
     * Sets up the configuration settings, XWork configuration, and
     * message resources
     */
    public static void setUp() throws Exception {
        LocalizedTextUtil.clearDefaultResourceBundles();
    }
    
    public static Dispatcher initDispatcher(Map<String,String> params) {
        if (params == null) {
            params = new HashMap<String,String>();
        }
        Dispatcher du = new Dispatcher(new MockServletContext(), params);
        du.init();
        Dispatcher.setInstance(du);
        return du;
    }

    public static void tearDown() throws Exception {
        Dispatcher.setInstance(null);
    }
}
