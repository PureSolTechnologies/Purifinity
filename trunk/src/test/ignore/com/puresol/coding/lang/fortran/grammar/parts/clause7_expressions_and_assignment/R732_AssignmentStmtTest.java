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
		// assertTrue(FortranGrammarPartTester.test("assignment-stmt",
		// "      A = 3.5 + X * Y\n"));
		// assertTrue(FortranGrammarPartTester.test("assignment-stmt",
		// "      I = INT (A)\n"));
		// assertTrue(FortranGrammarPartTester.test("assignment-stmt",
		// "      NAME = 'Dr. '//FIRST_NAME//' '//SURNAME\n"));
		// assertTrue(FortranGrammarPartTester.test("assignment-stmt",
		// "      NAME(:) = 'Dr. '//FIRST_NAME//' '//SURNAME\n"));
		// assertTrue(FortranGrammarPartTester.test("assignment-stmt",
		// "      A(I, J) = REAL (I + J - 2)\n"));
		// assertTrue(FortranGrammarPartTester.test("assignment-stmt",
		// "      B(I, J) = A(I, J) + B(I, J) * REAL (I * J)\n"));
		// assertTrue(FortranGrammarPartTester.test("assignment-stmt",
		// "      XX=(2.-EVA12)/EVA12\n"));

		assertTrue(FortranGrammarPartTester.test("assignment-stmt",
		"      A = 1 * 2 + 3 * 4 + 5 * 6\n"));
		assertTrue(FortranGrammarPartTester.test("assignment-stmt",
		"      HS = RH01 * SA0(I) + RH01 * SA0(I) + RH01 * SA0(I)\n"));
		assertTrue(FortranGrammarPartTester.test("assignment-stmt",
		"      HS = RHO1 * SA0(I) + RHO2 * QA0(I) + RHO3 * QN0(I)\n"));

	}
}
