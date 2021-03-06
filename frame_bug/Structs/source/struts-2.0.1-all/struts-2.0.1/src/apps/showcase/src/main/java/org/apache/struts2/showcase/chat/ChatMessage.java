/*
 * $Id: ChatMessage.java 440597 2006-09-06 03:34:39Z wsmoak $
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
package org.apache.struts2.showcase.chat;

import java.util.Date;

public class ChatMessage {
	
	private Date creationDate;
	private String message;
	private User creator;
	
	public ChatMessage(String message, User creator) {
		assert(message != null);
		assert(creator != null);
		
		this.creationDate = new Date(System.currentTimeMillis());
		this.message = message;
		this.creator = creator;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	public User getCreator() {
		return creator;
	}
	public String getMessage() {
		return message;
	}
}
