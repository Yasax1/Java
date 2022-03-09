/*
 * $Id: ListPeople.java 420385 2006-07-10 00:57:05Z mrdon $
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
package org.apache.struts2.showcase.person;

import com.opensymphony.xwork2.ActionSupport;

import java.util.List;
import java.util.ArrayList;

/**
 */
public class ListPeople extends ActionSupport {
	
	private static final long serialVersionUID = 3608017189783645371L;
	
	PersonManager personManager;
    List people = new ArrayList();

    public void setPersonManager(PersonManager personManager) {
        this.personManager = personManager;
    }

    public String execute() {
        people.addAll(personManager.getPeople());

        return SUCCESS;
    }

    public List getPeople() {
        return people;
    }

    public int getPeopleCount() {
        return people.size();
    }

}
