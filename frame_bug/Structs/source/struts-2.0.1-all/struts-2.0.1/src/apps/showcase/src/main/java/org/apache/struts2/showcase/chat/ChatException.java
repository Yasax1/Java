/*
 * $Id: ChatException.java 440597 2006-09-06 03:34:39Z wsmoak $
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

public class ChatException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public enum ErrorType {
		ROOM_ALREADY_EXISTS, 
		USER_ALREADY_EXISTS,
		NO_SUCH_ROOM_EXISTS
	}
	
	public ChatException(String description, ErrorType type) {
		super(description);
	}
}
