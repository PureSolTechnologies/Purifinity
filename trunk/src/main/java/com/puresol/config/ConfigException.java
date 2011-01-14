/***************************************************************************
 *
 * Copyright 2009-2010 PureSol Technologies 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 *
 ***************************************************************************/

package com.puresol.config;

import java.io.IOException;

/**
 * This exception is thrown in a case of any failure of the configuration
 * handling sub system.
 * 
 * @author Rick-Rainer Ludwig
 */
public class ConfigException extends IOException {

	private static final long serialVersionUID = 1L;

	/**
	 * This is the standard constructor if ConfigException. A message is given
	 * for the error message.
	 * 
	 * @param message
	 *            is a String containing the error message.
	 */
	public ConfigException(String message) {
		super(message);
	}
}
