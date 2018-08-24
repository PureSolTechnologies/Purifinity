package com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.parts.clause4_types;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.FortranGrammarPartTester;

public class R417_ComplexLiteralConstantIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("complex-literal-constant",
		"(A,B)"));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("complex-literal-constant",
		"(1,-2)"));
    }

    @Test
    public void test3() throws Exception {
	assertTrue(FortranGrammarPartTester.test("complex-literal-constant",
		"(+1.23,-4.56)"));
    }

}
