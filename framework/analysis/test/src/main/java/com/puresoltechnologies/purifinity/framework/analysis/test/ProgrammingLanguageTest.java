package com.puresoltechnologies.purifinity.framework.analysis.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.test.TestLanguage;

public class ProgrammingLanguageTest {

    @Test
    public void testGetInstance() {
	TestLanguage pl1 = TestLanguage.getInstance();
	assertNotNull(pl1);
	TestLanguage pl2 = TestLanguage.getInstance();
	assertNotNull(pl2);
	assertSame(pl1, pl2);
    }
}
