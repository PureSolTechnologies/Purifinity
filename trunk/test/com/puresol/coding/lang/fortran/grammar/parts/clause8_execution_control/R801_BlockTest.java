package com.puresol.coding.lang.fortran.grammar.parts.clause8_execution_control;

import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class R801_BlockTest extends TestCase {

	@Test
	public void test() {
		assertTrue(GrammarPartTester.test("block", "A=1\nB=2"));
		assertTrue(GrammarPartTester.test("block",
				"B = SQRT (A) \n"
						+ "C = LOG (A) \n"));
	}

}
