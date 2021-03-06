/*
 * $Id: TestSettings.java 425615 2006-07-26 04:33:53Z mrdon $
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * TestSettings
 *
 */
public class TestSettings extends Settings {

    /**
     * Get a named setting.
     *
     * @throws IllegalArgumentException if there is no settings parameter with the given name.
     */
    public String getImpl(String aName) throws IllegalArgumentException {
        return aName;
    }

    /**
     * List setting names
     */
    public Iterator listImpl() {
        List testList = new ArrayList();
        testList.add("123");
        testList.add("testValue");

        return testList.iterator();
    }
}
