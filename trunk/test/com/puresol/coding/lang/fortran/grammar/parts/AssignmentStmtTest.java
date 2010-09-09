package com.puresol.coding.lang.fortran.grammar.parts;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class AssignmentStmtTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("assignment-stmt", "A = 3.5 + X * Y"));
		assertTrue(GrammarPartTester.test("assignment-stmt", "I = INT (A)"));
		assertTrue(GrammarPartTester.test("assignment-stmt",
				"NAME = 'Dr. '//FIRST_NAME//' '//SURNAME"));
		assertTrue(GrammarPartTester.test("assignment-stmt",
				"NAME(:) = 'Dr. '//FIRST_NAME//' '//SURNAME"));
		assertTrue(GrammarPartTester.test("assignment-stmt",
				"A(I, J) = REAL (I + J - 2)"));
		assertTrue(GrammarPartTester.test("assignment-stmt",
				"B(I, J) = A(I, J) + B(I, J) * REAL (I * J)"));
	}
}
