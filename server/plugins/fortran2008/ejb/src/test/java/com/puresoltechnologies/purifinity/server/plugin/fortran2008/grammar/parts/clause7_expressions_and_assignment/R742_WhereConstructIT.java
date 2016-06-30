package com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.parts.clause7_expressions_and_assignment;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.FortranGrammarPartTester;

public class R742_WhereConstructIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("where-construct",//
		"      WHERE (PRESSURE <= 1.0) \n",//
		"      END WHERE               \n"//
	));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("where-construct",//
		"      WHERE (PRESSURE <= 1.0) \n",//
		"      A=1                     \n",//
		"      END WHERE               \n"//
	));
    }

    @Test
    public void test3() throws Exception {
	assertTrue(FortranGrammarPartTester.test("where-construct",//
		"      WHERE (PRESSURE <= 1.0)           \n",//
		"      PRESSURE = PRESSURE + INC_PRESSURE\n",//
		"      TEMP = TEMP - 5.0                 \n",//
		"      ELSEWHERE                         \n",//
		"      RAINING = .TRUE.                  \n",//
		"      END WHERE                         \n"//
	));
    }

}
