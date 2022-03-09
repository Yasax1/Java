/*
 * $Id: StrutsTestCase.java 462522 2006-10-10 19:42:16Z mrdon $
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
package org.apache.struts2;

import org.apache.struts2.config.Settings;
import org.apache.struts2.config.StrutsXmlConfigurationProvider;
import org.apache.struts2.dispatcher.Dispatcher;
import org.springframework.mock.web.MockServletContext;

import com.opensymphony.xwork2.XWorkTestCase;
import com.opensymphony.xwork2.config.ConfigurationManager;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

/**
 * Base test case for unit testing Struts.
 */
public abstract class StrutsTestCase extends XWorkTestCase {


    /**
     * Sets up the configuration settings, XWork configuration, and
     * message resources
     */
    protected void setUp() throws Exception {
        super.setUp();
        Settings.reset();
        LocalizedTextUtil.clearDefaultResourceBundles();
        Dispatcher du = new Dispatcher(new MockServletContext());
        Dispatcher.setInstance(du);
        configurationManager = new ConfigurationManager();
        configurationManager.addConfigurationProvider(
                new StrutsXmlConfigurationProvider("struts-default.xml", false));
        configurationManager.addConfigurationProvider(
                new StrutsXmlConfigurationProvider("struts-plugin.xml", false));
        configurationManager.addConfigurationProvider(
                new StrutsXmlConfigurationProvider("struts.xml", false));
        du.setConfigurationManager(configurationManager);
        
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
