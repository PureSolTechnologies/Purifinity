package com.puresol.coding.lang.fortran.grammar.parts.clause7_expressions_and_assignment;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R741_WhereStmtIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("where-stmt",
		"      WHERE (TEMP > 100.0) TEMP = TEMP - REDUCE_TEMP\n"));
    }

}
