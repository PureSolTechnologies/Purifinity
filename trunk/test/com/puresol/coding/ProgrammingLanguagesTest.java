package com.puresol.coding;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ProgrammingLanguagesTest extends TestCase {

	@Test
	public void testGetInstance() {
		ProgrammingLanguageManager pl1 = ProgrammingLanguageManager.getInstance();
		Assert.assertNotNull(pl1);
		ProgrammingLanguageManager pl2 = ProgrammingLanguageManager.getInstance();
		Assert.assertNotNull(pl2);
		Assert.assertSame(pl1, pl2);
	}

	@Test
	public void testGetLanguages() {
		ProgrammingLanguageManager pl = ProgrammingLanguageManager.getInstance();
		assertNotNull(pl);
	}

}
