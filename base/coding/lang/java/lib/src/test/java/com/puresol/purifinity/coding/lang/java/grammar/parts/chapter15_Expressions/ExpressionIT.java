package com.puresol.purifinity.coding.lang.java.grammar.parts.chapter15_Expressions;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.java.grammar.JavaGrammarPartTester;

public class ExpressionIT {

    @Test
    public void testConstants() throws Exception {
	assertTrue(JavaGrammarPartTester.test("Expression", "1"));
    }

    @Test
    public void testAssignment() throws Exception {
	assertTrue(JavaGrammarPartTester.test("Expression", "a = b"));
    }

    @Test
    public void testMethodInvocationExpression() throws Exception {
	assertTrue(JavaGrammarPartTester.test("Expression",
		"Globals.class.getResourceAsStream(\"/build.id\")"));
    }

}
