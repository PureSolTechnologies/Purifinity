package com.puresoltechnologies.purifinity.server.test.lang;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class TestLanguageTest {

	@Test
	public void testSingleton() {
		TestLanguage testLanguage = TestLanguage.getInstance();
		assertNotNull(testLanguage);
		assertSame(testLanguage, TestLanguage.getInstance());
	}
}
