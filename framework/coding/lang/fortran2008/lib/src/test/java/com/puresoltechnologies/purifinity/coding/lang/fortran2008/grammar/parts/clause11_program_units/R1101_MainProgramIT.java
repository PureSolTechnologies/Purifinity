package com.puresoltechnologies.purifinity.coding.lang.fortran2008.grammar.parts.clause11_program_units;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.coding.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R1101_MainProgramIT {

    @Test
    public void testEmptyMainProgram() throws Exception {
	assertTrue(FortranGrammarPartTester.test("main-program",//
		"PROGRAM TEST\n",//
		"      END PROGRAM\n"//
	));
    }
}
