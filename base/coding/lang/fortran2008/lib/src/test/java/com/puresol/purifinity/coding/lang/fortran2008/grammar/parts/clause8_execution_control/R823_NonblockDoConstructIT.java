package com.puresol.purifinity.coding.lang.fortran2008.grammar.parts.clause8_execution_control;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R823_NonblockDoConstructIT {

    @Test
    public void testSubroutine() throws Exception {
	assertTrue(FortranGrammarPartTester.test("nonblock-do-construct",//
		"      DO 30 I = 1,M               \n",//
		"      A(I,J) = A(I,J) + X(IX)*TEMP\n",//
		"      IX = IX + INCX              \n",//
		"      CONTINUE                    \n"//
	));
    }

}
