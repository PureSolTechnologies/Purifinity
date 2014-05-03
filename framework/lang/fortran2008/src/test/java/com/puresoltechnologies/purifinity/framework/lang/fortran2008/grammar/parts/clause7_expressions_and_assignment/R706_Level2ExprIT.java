package com.puresoltechnologies.purifinity.framework.lang.fortran2008.grammar.parts.clause7_expressions_and_assignment;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R706_Level2ExprIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("level-2-expr", "A"));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("level-2-expr", "B ** C"));
    }

    @Test
    public void test3() throws Exception {
	assertTrue(FortranGrammarPartTester.test("level-2-expr", "D * E"));
    }

    @Test
    public void test4() throws Exception {
	assertTrue(FortranGrammarPartTester.test("level-2-expr", "+1"));
    }

    @Test
    public void test5() throws Exception {
	assertTrue(FortranGrammarPartTester.test("level-2-expr", "F - I"));
    }

}
