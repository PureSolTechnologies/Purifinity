package com.puresol.coding.lang.fortran.grammar.parts.clause11_program_units;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R1101_MainProgramTest {

    @Test
    public void testEmptyMainProgram() throws Exception {
	assertTrue(FortranGrammarPartTester.test("main-program",
		"PROGRAM TEST\n" + "      END PROGRAM\n"));
    }
}
