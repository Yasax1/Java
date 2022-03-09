/*
 * $Id: HangmanException.java 440597 2006-09-06 03:34:39Z wsmoak $
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
package org.apache.struts2.showcase.hangman;

public class HangmanException extends RuntimeException {
	
	private static final long serialVersionUID = -8500292863595941335L;

	enum Type {
		GAME_ENDED, 
		NO_VOCAB, 
		NO_VOCAB_SOURCE;
	}
	
	
	private Type type;
	
	public HangmanException (Type type, String reason) {
		super(reason);
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
}
