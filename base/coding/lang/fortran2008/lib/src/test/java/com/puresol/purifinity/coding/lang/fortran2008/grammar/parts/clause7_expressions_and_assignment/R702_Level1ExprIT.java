package com.puresol.purifinity.coding.lang.fortran2008.grammar.parts.clause7_expressions_and_assignment;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R702_Level1ExprIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("level-1-expr", "A"));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("level-1-expr", ".INVERSE. B"));
    }

    @Test
    public void test3() throws Exception {
	assertTrue(FortranGrammarPartTester.test("level-1-expr",
		".INVERSE. (A + B)"));
    }

}
