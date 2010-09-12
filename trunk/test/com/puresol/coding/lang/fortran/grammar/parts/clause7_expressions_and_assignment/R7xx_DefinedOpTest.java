package com.puresol.coding.lang.fortran.grammar.parts.clause7_expressions_and_assignment;

import junit.framework.TestCase;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class R7xx_DefinedOpTest extends TestCase {

	@Test
	public void test() {
		assertTrue(GrammarPartTester.test("defined-op", ".INVERSE."));
		assertTrue(GrammarPartTester.test("defined-op", ".DEFINED."));
	}
}
