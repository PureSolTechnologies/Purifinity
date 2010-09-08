package com.puresol.coding.lang.fortran.grammar.parts;

import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class SignedIntLiteralConstantTest extends TestCase {

	@Test
	public void test() {
		assertTrue(GrammarPartTester.test("signed-int-literal-constant", "+1"));
		assertTrue(GrammarPartTester.test("signed-int-literal-constant", "0"));
		assertTrue(GrammarPartTester.test("signed-int-literal-constant", "-1"));
	}
		

}
