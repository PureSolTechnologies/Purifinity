package com.puresol.coding.lang.fortran.grammar.parts.clause7_expressions_and_assignment;

import junit.framework.TestCase;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R704_MultOperandTest extends TestCase {

	@Test
	public void test() {
		assertTrue(FortranGrammarPartTester.test("mult-operand", "1**2"));
		assertTrue(FortranGrammarPartTester.test("mult-operand", "(1+2)**(2+3)"));
	}
}
