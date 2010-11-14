package com.puresol.coding.lang.fortran.grammar.parts.clause8_execution_control;

import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R823_NonblockDoConstructTest extends TestCase {

	@Test
	public void testSubroutine() {
		fail();
		assertTrue(FortranGrammarPartTester.test("nonblock-do-construct",
				"      DO 30 I = 1,M                         \n"
						+ "      A(I,J) = A(I,J) + X(IX)*TEMP\n"
						+ "      IX = IX + INCX              \n"
						+ "      CONTINUE                    \n"));
	}

}
