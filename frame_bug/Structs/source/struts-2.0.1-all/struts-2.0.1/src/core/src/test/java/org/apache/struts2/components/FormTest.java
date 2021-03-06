/*
 * $Id: FormTest.java 439747 2006-09-03 09:22:46Z mrdon $
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
package org.apache.struts2.components;

import java.util.List;

import org.apache.struts2.TestAction;
import org.apache.struts2.views.jsp.AbstractUITagTest;

import com.opensymphony.xwork2.validator.validators.RequiredFieldValidator;

/**
 * <code>FormTest</code>
 *
 */
public class FormTest extends AbstractUITagTest {


    public void testTestFormGetValidators() {
        Form form = new Form(stack, request, response);
        form.getParameters().put("actionClass", TestAction.class);
        List v = form.getValidators("foo");
        assertEquals(1, v.size());
        assertEquals(RequiredFieldValidator.class, v.get(0).getClass());
    }
}
