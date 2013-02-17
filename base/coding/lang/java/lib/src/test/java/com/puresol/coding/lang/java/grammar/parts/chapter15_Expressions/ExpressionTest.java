package com.puresol.coding.lang.java.grammar.parts.chapter15_Expressions;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class ExpressionTest {

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