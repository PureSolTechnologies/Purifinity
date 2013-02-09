package com.puresol.coding.lang.fortran.grammar.parts.clause7_expressions_and_assignment;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R750_ForallConstructTest {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("forall-construct",
		"      FORALL (I = 1:10, J = 1:10, B(I, J) /= 0.0)         \n"
			+ "      A(I, J) = REAL (I + J - 2)                \n"
			+ "      B(I, J) = A(I, J) + B(I, J) * REAL (I * J)\n"
			+ "      END FORALL                                \n"));
    }

}
