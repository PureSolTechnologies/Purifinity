package com.puresol.purifinity.coding.lang.fortran2008.grammar.parts.clause5_attribute_declarations_and_specifications;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R508_LanguageBindingSpecIT {

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
