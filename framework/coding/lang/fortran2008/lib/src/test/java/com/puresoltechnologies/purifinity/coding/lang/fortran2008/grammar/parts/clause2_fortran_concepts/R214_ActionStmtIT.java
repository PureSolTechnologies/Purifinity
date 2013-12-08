package com.puresoltechnologies.purifinity.coding.lang.fortran2008.grammar.parts.clause2_fortran_concepts;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.coding.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R214_ActionStmtIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester
		.test("action-stmt",
			"      IF ((M.EQ.0) .OR. (N.EQ.0) .OR. (ALPHA.EQ.ZERO)) RETURN\n"));
    }

}
