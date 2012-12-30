package com.puresol.coding.lang.fortran.grammar.parts.clause5_attribute_declarations_and_specifications;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R508_LanguageBindingSpecTest {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("language-binding-spec",
		"BIND(C)"));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("language-binding-spec",
		"BIND(C,name=value)"));
    }

}
