package com.puresol.coding.lang.fortran.grammar.parts.clause4_types;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R406_SignedIntLiteralConstantTest {

    @Test
    public void testPositive() throws Exception {
	assertTrue(FortranGrammarPartTester.test("signed-int-literal-constant",
		"+1"));
    }

    @Test
    public void testZero() throws Exception {
	assertTrue(FortranGrammarPartTester.test("signed-int-literal-constant",
		"0"));
    }

    @Test
    public void testNegative() throws Exception {
	assertTrue(FortranGrammarPartTester.test("signed-int-literal-constant",
		"-1"));
    }
}
