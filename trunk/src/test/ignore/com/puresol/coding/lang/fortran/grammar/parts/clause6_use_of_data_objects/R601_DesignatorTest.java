package com.puresol.coding.lang.fortran.grammar.parts.clause6_use_of_data_objects;

import junit.framework.TestCase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R601_DesignatorTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("designator",
				"'ABCDEFGHIJKLMNOPQRSTUVWXYZ' (I:I)"));
		assertTrue(FortranGrammarPartTester.test("designator", "x"));
		assertTrue(FortranGrammarPartTester.test("designator", "x(1)"));
		assertTrue(FortranGrammarPartTester.test("designator", "'ABC' (1:2)"));
	}
}
