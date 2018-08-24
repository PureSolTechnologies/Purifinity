package com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.parts.clause3_lexical_tokens_and_source_form;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.FortranGrammarPartTester;

public class R304_ConstantIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("literal-constant", "1.0"));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("literal-constant", "0"));
    }

    @Test
    public void test3() throws Exception {
	assertTrue(FortranGrammarPartTester.test("literal-constant", "1.23"));
    }

    @Test
    public void test4() throws Exception {
	assertTrue(FortranGrammarPartTester.test("literal-constant", "(A,B)"));
    }

    @Test
    public void test5() throws Exception {
	assertTrue(FortranGrammarPartTester.test("literal-constant",
		"( 1.2 , 3.4 )"));
    }

    @Test
    public void test6() throws Exception {
	assertTrue(FortranGrammarPartTester.test("literal-constant", "'HALLO'"));
    }

    @Test
    public void test7() throws Exception {
	assertTrue(FortranGrammarPartTester.test("literal-constant", ".TRUE."));
    }

    @Test
    public void test8() throws Exception {
	assertTrue(FortranGrammarPartTester.test("literal-constant", ".FALSE."));
    }
}
