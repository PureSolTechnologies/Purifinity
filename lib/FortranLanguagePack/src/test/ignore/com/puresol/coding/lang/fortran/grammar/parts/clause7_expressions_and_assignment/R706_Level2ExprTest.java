package com.puresol.coding.lang.fortran.grammar.parts.clause7_expressions_and_assignment;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R706_Level2ExprTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("level-2-expr", "A"));
		assertTrue(FortranGrammarPartTester.test("level-2-expr", "B ** C"));
		assertTrue(FortranGrammarPartTester.test("level-2-expr", "D * E"));
		assertTrue(FortranGrammarPartTester.test("level-2-expr", "+1"));
		assertTrue(FortranGrammarPartTester.test("level-2-expr", "F - I"));
	}

}
