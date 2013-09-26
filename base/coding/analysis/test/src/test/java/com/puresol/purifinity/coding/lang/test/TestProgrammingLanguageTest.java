package com.puresol.purifinity.coding.lang.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.api.ProgrammingLanguage;

public class TestProgrammingLanguageTest {

	@Test
	public void testInstance() {
		assertNotNull(TestLanguage.getInstance());
	}

	@Test
	public void testInitialValues() {
		ProgrammingLanguage lang = TestLanguage.getInstance();
		assertEquals("TestLanguage", lang.getName());
	}

	@Test
	public void testSingleton() {
		TestLanguage java = TestLanguage.getInstance();
		assertNotNull(java);
		assertSame(java, TestLanguage.getInstance());
	}
}
