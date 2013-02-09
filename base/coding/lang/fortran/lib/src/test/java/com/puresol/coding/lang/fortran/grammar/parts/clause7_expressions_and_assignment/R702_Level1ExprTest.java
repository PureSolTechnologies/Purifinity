package com.puresol.coding.lang.fortran.grammar.parts.clause7_expressions_and_assignment;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R702_Level1ExprTest {

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
