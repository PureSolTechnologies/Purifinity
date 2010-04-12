package com.puresol.coding;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ProgrammingLanguagesTest extends TestCase {

    @Test
    public void testGetInstance() {
	ProgrammingLanguages pl1 = ProgrammingLanguages.getInstance();
	Assert.assertNotNull(pl1);
	ProgrammingLanguages pl2 = ProgrammingLanguages.getInstance();
	Assert.assertNotNull(pl2);
	Assert.assertSame(pl1, pl2);
    }

    @Test
    public void testGetLanguages() {
	ProgrammingLanguages pl = ProgrammingLanguages.getInstance();
	Assert.assertTrue(pl.getLanguages().size() > 0);
    }

}
