package com.puresol.coding.lang.fortran.grammar.parts.clause8_execution_control;

import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R801_BlockTest extends TestCase {

	@Test
	public void test() {
		assertTrue(FortranGrammarPartTester.test("block", "      A=1\n"
				+ "      B=2\n"));
		assertTrue(FortranGrammarPartTester.test("block", "     B = SQRT (A) \n"
				+ "     C = LOG (A) \n"));
	}

}
