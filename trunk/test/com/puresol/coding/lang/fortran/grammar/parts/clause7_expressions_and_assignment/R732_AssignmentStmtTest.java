package com.puresol.coding.lang.fortran.grammar.parts.clause7_expressions_and_assignment;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R732_AssignmentStmtTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("assignment-stmt",
				"      A = 3.5 + X * Y\n"));
		assertTrue(FortranGrammarPartTester.test("assignment-stmt",
				"      I = INT (A)\n"));
		assertTrue(FortranGrammarPartTester.test("assignment-stmt",
				"      NAME = 'Dr. '//FIRST_NAME//' '//SURNAME\n"));
		assertTrue(FortranGrammarPartTester.test("assignment-stmt",
				"      NAME(:) = 'Dr. '//FIRST_NAME//' '//SURNAME\n"));
		assertTrue(FortranGrammarPartTester.test("assignment-stmt",
				"      A(I, J) = REAL (I + J - 2)\n"));
		assertTrue(FortranGrammarPartTester.test("assignment-stmt",
				"      B(I, J) = A(I, J) + B(I, J) * REAL (I * J)\n"));
	}
}
