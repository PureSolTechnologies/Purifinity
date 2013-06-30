package com.puresol.purifinity.coding.lang.fortran.grammar.parts.clause8_execution_control;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R813_DoConstructIT {

    @Test
    public void testSubroutine() throws Exception {
	assertTrue(FortranGrammarPartTester.test("do-construct",//
		"      DO I = 1,M                    \n",//
		"      A(I,J) = A(I,J) + X(IX)*TEMP  \n",//
		"      IX = IX + INCX                \n",//
		"      END DO                        \n"//
	));
    }

}
