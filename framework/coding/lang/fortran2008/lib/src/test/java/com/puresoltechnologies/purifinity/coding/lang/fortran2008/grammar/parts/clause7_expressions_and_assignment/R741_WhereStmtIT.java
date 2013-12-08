package com.puresoltechnologies.purifinity.coding.lang.fortran2008.grammar.parts.clause7_expressions_and_assignment;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.coding.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R741_WhereStmtIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("where-stmt",
		"      WHERE (TEMP > 100.0) TEMP = TEMP - REDUCE_TEMP\n"));
    }

}
