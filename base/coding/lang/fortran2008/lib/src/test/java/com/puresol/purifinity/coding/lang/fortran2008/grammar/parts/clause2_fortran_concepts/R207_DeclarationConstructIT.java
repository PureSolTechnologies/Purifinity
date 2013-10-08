package com.puresol.purifinity.coding.lang.fortran2008.grammar.parts.clause2_fortran_concepts;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R207_DeclarationConstructIT {

    @Test
    public void test1() throws Exception {
	assertTrue(FortranGrammarPartTester.test("declaration-construct",
		"PARAMETER(INOM2 =  2 * INOM)\n"));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("declaration-construct",
		"REAL(8) CROSR\n"));
    }
}
