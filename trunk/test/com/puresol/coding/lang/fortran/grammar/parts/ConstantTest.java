package com.puresol.coding.lang.fortran.grammar.parts;

import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class ConstantTest extends TestCase {

	@Test
	public void test() {
		assertTrue(GrammarPartTester.test("constant", "1.0"));
		assertTrue(GrammarPartTester.test("constant", "0"));
		assertTrue(GrammarPartTester.test("constant", "1.23"));
		assertTrue(GrammarPartTester.test("constant", "(A,B)"));
		assertTrue(GrammarPartTester.test("constant", "( 1.2 , 3.4 )"));
		assertTrue(GrammarPartTester.test("constant", "'HALLO'"));
		assertTrue(GrammarPartTester.test("constant", ".TRUE."));
		assertTrue(GrammarPartTester.test("constant", ".FALSE."));
	}

}
