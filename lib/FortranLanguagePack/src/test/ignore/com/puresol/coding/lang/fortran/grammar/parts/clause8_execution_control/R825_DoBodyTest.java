package com.puresol.coding.lang.fortran.grammar.parts.clause8_execution_control;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R825_DoBodyTest extends TestCase {

	@Test
	public void testSubroutine() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("do-body",
				"      A(I,J) = A(I,J) + X(IX)*TEMP  \n"
						+ "      IX = IX + INCX      \n"));
	}

}
