package com.puresol.coding.lang.fortran.grammar.parts.clause8_execution_control;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R810_CriticalConstructIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("critical-construct",//
		"      CRITICAL    \n",//
		"      END CRITICAL\n"//
	));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("critical-construct",//
		"      CRITICAL                                      \n",//
		"          GLOBAL_COUNTER[1] = GLOBAL_COUNTER[1] + 1 \n",//
		"      END CRITICAL                                  \n"//
	));
    }

}
