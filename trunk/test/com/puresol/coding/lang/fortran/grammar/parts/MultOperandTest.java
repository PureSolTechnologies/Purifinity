package com.puresol.coding.lang.fortran.grammar.parts;

import junit.framework.TestCase;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class MultOperandTest extends TestCase {

	@Test
	public void test() {
		assertTrue(GrammarPartTester.test("mult-operand", "1**2"));
		assertTrue(GrammarPartTester.test("mult-operand", "(1+2)**(2+3)"));
	}
}
