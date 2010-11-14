package com.puresol.coding.lang.fortran.grammar.parts.clause7_expressions_and_assignment;

import junit.framework.TestCase;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R7xx_DefinedOpTest extends TestCase {

	@Test
	public void test() {
		assertTrue(FortranGrammarPartTester.test("defined-op", ".INVERSE."));
		assertTrue(FortranGrammarPartTester.test("defined-op", ".DEFINED."));
	}
}
