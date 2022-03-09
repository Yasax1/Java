/*
 * $Id: ObjectFactoryInitializable.java 418521 2006-07-01 23:36:50Z mrdon $
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
package org.apache.struts2.util;

import javax.servlet.ServletContext;

/**
 * Used to pass ServletContext init parameters to various
 * frameworks such as Spring, Plexus and Portlet.
 */
public interface ObjectFactoryInitializable {

    void init(ServletContext servletContext);

}
