package com.puresoltechnologies.purifinity.framework.lang.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.test.TestLanguage;

public class TestLanguageTest {

    @Test
    public void testSingleton() {
	TestLanguage testLanguage = TestLanguage.getInstance();
	assertNotNull(testLanguage);
	assertSame(testLanguage, TestLanguage.getInstance());
    }
}
