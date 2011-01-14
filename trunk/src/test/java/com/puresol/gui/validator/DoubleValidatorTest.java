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

package com.puresol.gui.validator;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoubleValidatorTest {

	@Test
	public void testValid() {
		DoubleValidator validator = new DoubleValidator();
		assertTrue(validator.isValid(1));
		assertTrue(validator.isValid("1.1"));
		assertTrue(validator.isValid("0"));
		assertTrue(validator.isValid("-1.1"));
		assertTrue(validator.isValid("42"));
		assertTrue(validator.isValid("1.2345e+6"));
	}

	@Test
	public void testInvalid() {
		DoubleValidator validator = new DoubleValidator();
		assertFalse(validator.isValid("string"));
		assertFalse(validator.isValid(true));
	}
}
