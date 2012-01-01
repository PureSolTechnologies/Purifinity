package com.puresol.coding;

import org.junit.Test;

import com.puresol.coding.lang.test.TestLanguage;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ProgrammingLanguageTest extends TestCase {

    @Test
    public void testGetInstance() {
	TestLanguage pl1 = TestLanguage.getInstance();
	Assert.assertNotNull(pl1);
	TestLanguage pl2 = TestLanguage.getInstance();
	Assert.assertNotNull(pl2);
	Assert.assertSame(pl1, pl2);
    }
}
