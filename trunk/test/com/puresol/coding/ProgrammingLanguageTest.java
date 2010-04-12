package com.puresol.coding;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ProgrammingLanguageTest extends TestCase {

    @Test
    public void testGetInstance() {
	TestProgrammingLanguage pl1 = TestProgrammingLanguage.getInstance();
	Assert.assertNotNull(pl1);
	TestProgrammingLanguage pl2 = TestProgrammingLanguage.getInstance();
	Assert.assertNotNull(pl2);
	Assert.assertSame(pl1, pl2);
    }
}
