/*
 * $Id: Gangster.java 418530 2006-07-01 23:58:13Z mrdon $
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
package org.apache.struts2.showcase.modelDriven;

import java.io.Serializable;

/**
 * A model class to be used by the simple Model-Driven example. 
 * 
 */
public class Gangster implements Serializable {

	private static final long serialVersionUID = 3688389475320294992L;
	
	private String name;
	private int age;
	private String description;
	private boolean bustedBefore;
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isBustedBefore() {
		return bustedBefore;
	}
	public void setBustedBefore(boolean bustedBefore) {
		this.bustedBefore = bustedBefore;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
