package com.puresol.purifinity.coding.lang.java.grammar.parts.chapter14_BlocksAndStatements;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.java.grammar.JavaGrammarPartTester;

public class StatementExpressionIT {

    @Test
    public void testConstants() throws Exception {
	assertTrue(JavaGrammarPartTester.test("StatementExpression", "a = b"));
    }
}