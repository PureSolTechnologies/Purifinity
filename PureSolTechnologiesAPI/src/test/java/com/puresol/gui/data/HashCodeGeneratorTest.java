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

package com.puresol.gui.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class HashCodeGeneratorTest {

	@Test
	public void testMD5() {
		String code = HashCodeGenerator.getMD5("TEST!");
		assertEquals("7c36f14325954cd6cf996f8ee1261d56", code);
	}

	@Test
	public void testSHA() {
		String code = HashCodeGenerator.getSHA("TEST!");
		assertEquals("c079fbc11f067bfa8115f53341c9d455a56c6", code);
	}
}
