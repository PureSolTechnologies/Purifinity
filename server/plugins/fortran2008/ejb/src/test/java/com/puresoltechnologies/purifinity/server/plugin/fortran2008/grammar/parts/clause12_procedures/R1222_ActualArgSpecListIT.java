package com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.parts.clause12_procedures;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.FortranGrammarPartTester;

public class R1222_ActualArgSpecListIT {

    @Test
    public void test3() throws Exception {
	assertTrue(FortranGrammarPartTester.test("actual-arg-spec-list", "A"));
    }

    @Test
    public void test4() throws Exception {
	assertTrue(FortranGrammarPartTester
		.test("actual-arg-spec-list", "A, B"));
    }

    @Test
    public void test5() throws Exception {
	assertTrue(FortranGrammarPartTester.test("actual-arg-spec-list",
		"A, B, C"));
    }

    @Test
    public void test6() throws Exception {
	assertTrue(FortranGrammarPartTester.test("actual-arg-spec-list",
		"N, X, INCX, Y, INCY"));
    }
}
