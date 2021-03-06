package com.puresoltechnologies.purifinity.server.test.lang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.puresoltechnologies.purifinity.analysis.domain.ProgrammingLanguage;

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
