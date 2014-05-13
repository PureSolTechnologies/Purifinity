package com.puresoltechnologies.purifinity.framework.lang.fortran2008.grammar.parts.clause4_types;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R406_SignedIntLiteralConstantIT {

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
