package com.puresoltechnologies.purifinity.framework.lang.fortran2008.grammar.parts.clause8_execution_control;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.fortran2008.grammar.FortranGrammarPartTester;

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
