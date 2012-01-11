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

package com.puresol.log;

import org.apache.log4j.Level;

/**
 * This LogLevel is defined just to introduce a new level which is always logged
 * for information, but is not to be suppressed like log settings changes, which
 * are useful to know for reading logs, but it should always be there.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LogAlways extends Level {

	private static final long serialVersionUID = 1L;
	public static final Level LOG_ALWAYS = new LogAlways();

	protected LogAlways() {
		super(100000, "INFO!", 7);
	}

}
