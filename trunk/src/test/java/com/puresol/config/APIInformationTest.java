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

import static org.junit.Assert.*;

import org.junit.Test;

public class APIInformationTest {

	@Test
	public void testGettersAndSetters() {
		APIInformation.setHelpRequest(1);
		APIInformation.setVersionRequest(2);
		assertEquals(Integer.valueOf(1),
				Integer.valueOf(APIInformation.getHelpRequest()));
		assertEquals(Integer.valueOf(2),
				Integer.valueOf(APIInformation.getVersionRequest()));
	}

	@Test
	public void testGetPackageVersion() {
		String string = APIInformation.getPackageVersion();
		assertNotNull(string);
		assertFalse(string.isEmpty());
		assertEquals(ConfigFile.readEntry("src/main/resources/config/about",
				"GENERAL", "version"), string);
	}

	@Test
	public void testGetPackageYears() {
		String string = APIInformation.getPackageYears();
		assertNotNull(string);
		assertFalse(string.isEmpty());
		assertEquals(ConfigFile.readEntry("src/main/resources/config/about",
				"GENERAL", "years"), string);
	}

	@Test
	public void testGetPackageAuthor() {
		String string = APIInformation.getPackageAuthor();
		assertNotNull(string);
		assertFalse(string.isEmpty());
		assertEquals(ConfigFile.readEntry("src/main/resources/config/about",
				"GENERAL", "author"), string);
	}

	@Test
	public void testGetPackageBugReport() {
		String string = APIInformation.getPackageBugReport();
		assertNotNull(string);
		assertFalse(string.isEmpty());
		assertEquals(ConfigFile.readEntry("src/main/resources/config/about",
				"GENERAL", "bugreport"), string);
	}

	@Test
	public void testGetPackageCopyRight() {
		String string = APIInformation.getPackageCopyright();
		assertNotNull(string);
		assertFalse(string.isEmpty());
		assertEquals(ConfigFile.readEntry("src/main/resources/config/about",
				"GENERAL", "copyright"), string);
	}

	@Test
	public void testGetPackageOwner() {
		String string = APIInformation.getPackageOwner();
		assertNotNull(string);
		assertFalse(string.isEmpty());
		assertEquals(ConfigFile.readEntry("src/main/resources/config/about",
				"GENERAL", "owner"), string);
	}

}
