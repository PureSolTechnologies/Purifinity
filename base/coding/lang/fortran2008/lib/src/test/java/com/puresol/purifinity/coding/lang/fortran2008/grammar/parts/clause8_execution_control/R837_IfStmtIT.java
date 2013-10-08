package com.puresol.purifinity.coding.lang.fortran2008.grammar.parts.clause8_execution_control;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R837_IfStmtIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("if-stmt",
		"      IF (A > 0.0) A = LOG (A)\n"));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester
		.test("if-stmt",
			"      IF ((M.EQ.0) .OR. (N.EQ.0) .OR. (ALPHA.EQ.ZERO)) RETURN\n"));
    }

}
