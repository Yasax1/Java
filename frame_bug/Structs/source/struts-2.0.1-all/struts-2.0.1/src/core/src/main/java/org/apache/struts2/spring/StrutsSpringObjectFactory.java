/*
 * $Id: StrutsSpringObjectFactory.java 439747 2006-09-03 09:22:46Z mrdon $
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
package org.apache.struts2.spring;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsConstants;
import org.apache.struts2.config.Settings;
import org.apache.struts2.util.ObjectFactoryInitializable;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.spring.SpringObjectFactory;



/**
 * Struts object factory that integrates with Spring.
 * <p/>
 * Spring should be loaded using a web context listener
 * <code>org.springframework.web.context.ContextLoaderListener</code> defined in <code>web.xml</code>.
 *
 */
public class StrutsSpringObjectFactory extends SpringObjectFactory implements ObjectFactoryInitializable {
    private static final Log log = LogFactory.getLog(StrutsSpringObjectFactory.class);

    /* (non-Javadoc)
     * @see org.apache.struts2.util.ObjectFactoryInitializable#init(javax.servlet.ServletContext)
     */
    public void init(ServletContext servletContext) {
        log.info("Initializing Struts-Spring integration...");

        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        if (appContext == null) {
            // uh oh! looks like the lifecycle listener wasn't installed. Let's inform the user
            String message = "********** FATAL ERROR STARTING UP SPRING-STRUTS INTEGRATION **********\n" +
                    "Looks like the Spring listener was not configured for your web app! \n" +
                    "Nothing will work until WebApplicationContextUtils returns a valid ApplicationContext.\n" +
                    "You might need to add the following to web.xml: \n" +
                    "    <listener>\n" +
                    "        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>\n" +
                    "    </listener>";
            log.fatal(message);
            return;
        }

        this.setApplicationContext(appContext);

        String autoWire = Settings.get(StrutsConstants.STRUTS_OBJECTFACTORY_SPRING_AUTOWIRE);
        int type = AutowireCapableBeanFactory.AUTOWIRE_BY_NAME;   // default
        if ("name".equals(autoWire)) {
            type = AutowireCapableBeanFactory.AUTOWIRE_BY_NAME;
        } else if ("type".equals(autoWire)) {
            type = AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE;
        } else if ("auto".equals(autoWire)) {
            type = AutowireCapableBeanFactory.AUTOWIRE_AUTODETECT;
        } else if ("constructor".equals(autoWire)) {
            type = AutowireCapableBeanFactory.AUTOWIRE_CONSTRUCTOR;
        }
        this.setAutowireStrategy(type);

        boolean useClassCache = "true".equals(Settings.get(StrutsConstants.STRUTS_OBJECTFACTORY_SPRING_USE_CLASS_CACHE));
        this.setUseClassCache(useClassCache);

        log.info("... initialized Struts-Spring integration successfully");
    }
}
