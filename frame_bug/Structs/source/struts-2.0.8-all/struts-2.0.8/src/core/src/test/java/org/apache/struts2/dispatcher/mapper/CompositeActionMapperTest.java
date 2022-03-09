/*
 * $Id: CompositeActionMapperTest.java 478625 2006-11-23 17:31:52Z wsmoak $
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
package org.apache.struts2.dispatcher.mapper;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsConstants;
import org.springframework.mock.web.MockHttpServletRequest;

import com.mockobjects.dynamic.C;
import com.mockobjects.dynamic.Mock;
import com.opensymphony.xwork2.config.ConfigurationManager;
import com.opensymphony.xwork2.inject.Container;
import com.opensymphony.xwork2.inject.Scope.Strategy;

import junit.framework.TestCase;

/**
 *
 * @version $Date: 2006-11-23 18:31:52 +0100 (Thu, 23 Nov 2006) $ $Id: CompositeActionMapperTest.java 478625 2006-11-23 17:31:52Z wsmoak $
 */
public class CompositeActionMapperTest extends TestCase {

    CompositeActionMapper compositeActionMapper;
    Mock mockContainer;
    
    public void setUp() throws Exception {
        compositeActionMapper = new CompositeActionMapper();
        mockContainer = new Mock(Container.class);
        compositeActionMapper.setContainer((Container)mockContainer.proxy());
    }
    

    public void testGetActionMappingAndUri1() throws Exception {
        ActionMapper mapper1 = new InnerActionMapper1();
        ActionMapper mapper2 = new InnerActionMapper2();
        ActionMapper mapper3 = new InnerActionMapper3();
        mockContainer.expectAndReturn("getInstance", C.args(C.eq(ActionMapper.class), C.eq("mapper1")), mapper1);
        mockContainer.expectAndReturn("getInstance", C.args(C.eq(ActionMapper.class), C.eq("mapper2")), mapper3);
        mockContainer.expectAndReturn("getInstance", C.args(C.eq(ActionMapper.class), C.eq("mapper3")), mapper2);
        compositeActionMapper.setActionMappers("mapper1,mapper2,mapper3");
        
        ActionMapping actionMapping = compositeActionMapper.getMapping(new MockHttpServletRequest(), new ConfigurationManager());
        String uri = compositeActionMapper.getUriFromActionMapping(new ActionMapping());
        mockContainer.verify();
        
        assertNotNull(actionMapping);
        assertNotNull(uri);
        assertTrue(actionMapping == InnerActionMapper3.actionMapping);
        assertTrue(uri == InnerActionMapper3.uri);
        
    }

    public void testGetActionMappingAndUri2() throws Exception {
        ActionMapper mapper1 = new InnerActionMapper1();
        ActionMapper mapper2 = new InnerActionMapper2();
        mockContainer.expectAndReturn("getInstance", C.args(C.eq(ActionMapper.class), C.eq("mapper1")), mapper1);
        mockContainer.expectAndReturn("getInstance", C.args(C.eq(ActionMapper.class), C.eq("mapper2")), mapper2);
        compositeActionMapper.setActionMappers("mapper1,mapper2");

        ActionMapping actionMapping = compositeActionMapper.getMapping(new MockHttpServletRequest(), new ConfigurationManager());
        String uri = compositeActionMapper.getUriFromActionMapping(new ActionMapping());
        mockContainer.verify();

        assertNull(actionMapping);
        assertNull(uri);
    }


    public static class InnerActionMapper1 implements ActionMapper {
        public static ActionMapping actionMapping = new ActionMapping();
        public static String uri="uri1";

        public ActionMapping getMapping(HttpServletRequest request, ConfigurationManager configManager) {
            return null;
        }
        public String getUriFromActionMapping(ActionMapping mapping) {
            return null;
        }
    }
    public static class InnerActionMapper2 implements ActionMapper {
        public static ActionMapping actionMapping = new ActionMapping();
        public static String uri="uri2";

        public ActionMapping getMapping(HttpServletRequest request, ConfigurationManager configManager) {
            return null;
        }
        public String getUriFromActionMapping(ActionMapping mapping) {
            return null;
        }
    }
    public static class InnerActionMapper3 implements ActionMapper {
        public static ActionMapping actionMapping = new ActionMapping();
        public static String uri = "uri3";

        public ActionMapping getMapping(HttpServletRequest request, ConfigurationManager configManager) {
            return actionMapping;
        }
        public String getUriFromActionMapping(ActionMapping mapping) {
            return uri;
        }
    }
}
