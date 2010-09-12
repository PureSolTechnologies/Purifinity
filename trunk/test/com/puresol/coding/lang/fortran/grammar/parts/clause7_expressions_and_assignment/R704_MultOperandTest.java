package com.puresol.coding.lang.fortran.grammar.parts.clause7_expressions_and_assignment;

import junit.framework.TestCase;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class R704_MultOperandTest extends TestCase {

	@Test
	public void test() {
		assertTrue(GrammarPartTester.test("mult-operand", "1**2"));
		assertTrue(GrammarPartTester.test("mult-operand", "(1+2)**(2+3)"));
	}
}
