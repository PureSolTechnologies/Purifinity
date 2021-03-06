package com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.parts.clause7_expressions_and_assignment;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.FortranGrammarPartTester;

public class R722_ExprIT {

    @Test
    public void testExpression() throws Exception {
	assertTrue(FortranGrammarPartTester.test("expr", "A"));
    }

    @Test
    public void testExpression2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("expr", "INOM * 2"));
    }

    @Test
    public void testExpression3() throws Exception {
	assertTrue(FortranGrammarPartTester.test("expr", "A + B == C * D"));
    }

    @Test
    public void testExpression4() throws Exception {
	assertTrue(FortranGrammarPartTester.test("expr",
		"((M .EQ. 0) .OR. (N .EQ. 0) .OR. (ALPHA .EQ. ZERO))"));
    }

}
