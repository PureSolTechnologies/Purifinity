package com.puresol.coding.lang.fortran.grammar.parts;

import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class ComplexLiteralConstantTest extends TestCase {

	@Test
	public void test() {
		assertTrue(GrammarPartTester.test("complex-literal-constant", "(A,B)"));
		assertTrue(GrammarPartTester.test("complex-literal-constant", "(1,-2)"));
		assertTrue(GrammarPartTester.test("complex-literal-constant",
				"(+1.23,-4.56)"));
	}

}
