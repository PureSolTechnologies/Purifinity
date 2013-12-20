package com.puresoltechnologies.purifinity.framework.lang.fortran2008.grammar.parts.clause7_expressions_and_assignment;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R704_MultOperandIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("mult-operand", "1**2"));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester
		.test("mult-operand", "(1+2)**(2+3)"));
    }
}
