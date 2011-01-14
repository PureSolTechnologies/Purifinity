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

public class EMailAddressValidatorTest {

	@Test
	public void testValid() {
		EMailAddressValidator validator = new EMailAddressValidator();
		assertTrue(validator.isValid("a@a.de"));
		assertTrue(validator.isValid("rl719236@sourceforge.net"));
	}

	@Test
	public void testInvalid() {
		EMailAddressValidator validator = new EMailAddressValidator();
		assertFalse(validator.isValid("a@a.a"));
		assertFalse(validator.isValid("aa@aa.a"));
	}
}
