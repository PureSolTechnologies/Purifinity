package com.puresol.coding.lang.fortran.grammar.parts;

import junit.framework.TestCase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class DesignatorTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("designator",
				"'ABCDEFGHIJKLMNOPQRSTUVWXYZ' (I:I)"));
		assertTrue(GrammarPartTester.test("designator", "x"));
		assertTrue(GrammarPartTester.test("designator", "x(1)"));
		assertTrue(GrammarPartTester.test("designator", "'ABC' (1:2)"));
	}
}
