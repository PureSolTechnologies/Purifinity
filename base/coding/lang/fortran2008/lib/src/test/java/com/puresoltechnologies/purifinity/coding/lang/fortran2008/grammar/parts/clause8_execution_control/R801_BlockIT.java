package com.puresoltechnologies.purifinity.coding.lang.fortran2008.grammar.parts.clause8_execution_control;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.coding.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R801_BlockIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("block", //
		"A=1\n",//
		"B=2\n"//
	));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("block",//
		"B = SQRT (A) \n",//
		"C = LOG (A)  \n"//
	));
    }

}
