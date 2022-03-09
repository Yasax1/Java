/*
 * $Id: PropertiesSettings.java 425615 2006-07-26 04:33:53Z mrdon $
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
package org.apache.struts2.config;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

import org.apache.struts2.StrutsException;


/**
 * A class to handle settings via a properties file.
 */
public class PropertiesSettings extends Settings {

    Properties settings;


    /**
     * Creates a new properties config given the name of a properties file. The name is expected to NOT have
     * the ".properties" file extension.  So when <tt>new PropertiesSettings("foo")</tt> is called
     * this class will look in the classpath for the <tt>foo.properties</tt> file.
     *
     * @param name the name of the properties file, excluding the ".properties" extension.
     */
    public PropertiesSettings(String name) {
        settings = new Properties();

        URL settingsUrl = Thread.currentThread().getContextClassLoader().getResource(name + ".properties");

        if (settingsUrl == null) {
            throw new IllegalStateException(name + ".properties missing");
        }

        // Load settings
        try {
            settings.load(settingsUrl.openStream());
        } catch (IOException e) {
            throw new StrutsException("Could not load " + name + ".properties:" + e, e);
        }
    }


    /**
     * Sets a property in the properties file.
     *
     * @see #set(String, String)
     */
    public void setImpl(String aName, String aValue) {
        settings.setProperty(aName, aValue);
    }

    /**
     * Gets a property from the properties file.
     *
     * @see #get(String)
     */
    public String getImpl(String aName) throws IllegalArgumentException {
        String setting = settings.getProperty(aName);

        if (setting == null) {
            throw new IllegalArgumentException("No such setting:" + aName);
        }

        return setting;
    }

    /**
     * Tests to see if a property exists in the properties file.
     *
     * @see #isSet(String)
     */
    public boolean isSetImpl(String aName) {
        if (settings.get(aName) != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Lists all keys in the properties file.
     *
     * @see #list()
     */
    public Iterator listImpl() {
        return settings.keySet().iterator();
    }
}
