package com.puresol.coding.lang.fortran.grammar.parts.clause8_execution_control;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R825_DoBodyIT {

    @Test
    public void testSubroutine() throws Exception {
	assertTrue(FortranGrammarPartTester.test("do-body",//
		"      A(I,J) = A(I,J) + X(IX)*TEMP \n",//
		"      IX = IX + INCX               \n"//
	));
    }

}
