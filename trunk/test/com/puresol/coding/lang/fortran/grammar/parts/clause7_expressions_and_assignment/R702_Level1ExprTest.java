package com.puresol.coding.lang.fortran.grammar.parts.clause7_expressions_and_assignment;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R702_Level1ExprTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("level-1-expr", "A"));
		assertTrue(FortranGrammarPartTester.test("level-1-expr", ".INVERSE. B"));
		assertTrue(FortranGrammarPartTester.test("level-1-expr", ".INVERSE. (A + B)"));
	}

}
