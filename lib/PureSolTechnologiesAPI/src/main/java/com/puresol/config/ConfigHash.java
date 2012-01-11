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

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * This Hash stores the data of a single configuration file.
 * 
 * @author Rick-Rainer Ludwig
 */
public class ConfigHash extends Hashtable<String, Hashtable<String, String>> {

	private static final long serialVersionUID = 1L;

	/**
	 * This method is used for debugging. It prints the content of the hash to
	 * the stdout.
	 */
	public void println() {
		Enumeration<String> sectionEnum = this.keys();
		while (sectionEnum.hasMoreElements()) {
			String section = sectionEnum.nextElement();
			System.out.println("[" + section + "]");
			Enumeration<String> keyEnum = get(section).keys();
			while (keyEnum.hasMoreElements()) {
				String key = keyEnum.nextElement();
				String value = get(section).get(key);
				System.out.println(key + "=" + value);
			}
		}
	}
}
