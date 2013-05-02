package com.puresol.coding.lang.fortran.grammar.parts.clause7_expressions_and_assignment;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R7xx_DefinedOpIT {

    @Test
    public void testInverse() throws Exception {
	assertTrue(FortranGrammarPartTester.test("defined-op", ".INVERSE."));
    }

    @Test
    public void testDefined() throws Exception {
	assertTrue(FortranGrammarPartTester.test("defined-op", ".DEFINED."));
    }
}
