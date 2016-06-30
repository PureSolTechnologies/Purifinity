package com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.parts.clause7_expressions_and_assignment;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.FortranGrammarPartTester;

public class R750_ForallConstructIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("forall-construct",//
		"FORALL (I = 1:10, J = 1:10, B(I, J) /= 0.0)    \n",//
		"    A(I, J) = REAL (I + J - 2)                 \n",//
		"    B(I, J) = A(I, J) + B(I, J) * REAL (I * J) \n",//
		"END FORALL                                     \n"//
	));
    }

}
