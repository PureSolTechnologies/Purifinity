package com.puresoltechnologies.purifinity.server.test.analysis;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.test.lang.TestLanguage;

public class TestLanguageTest {

    @Test
    public void testGetInstanceSingleton() {
	TestLanguage pl1 = TestLanguage.getInstance();
	assertNotNull(pl1);
	TestLanguage pl2 = TestLanguage.getInstance();
	assertNotNull(pl2);
	assertSame(pl1, pl2);
    }
}
