package com.puresol.coding.lang.fortran.grammar.parts.clause7_expressions_and_assignment;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R722_ExprTest extends TestCase {

	@Test
	public void testExpression() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("expr", "A"));
		assertTrue(FortranGrammarPartTester.test("expr", "2 * INOM"));
		assertTrue(FortranGrammarPartTester.test("expr", "A + B == C * D"));
	}

	@Test
	public void testExpression2() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("expr",
				"((M .EQ. 0) .OR. (N .EQ. 0) .OR. (ALPHA .EQ. ZERO))"));
	}

}
