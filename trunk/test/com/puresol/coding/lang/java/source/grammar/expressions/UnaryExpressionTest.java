package com.puresol.coding.lang.java.source.grammar.expressions;

import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

public class UnaryExpressionTest extends TestCase {

	@Test
	public void testValids() {
		assertTrue(JavaGrammarTester.valid("+1", UnaryExpression.class));
		assertTrue(JavaGrammarTester.valid("-1", UnaryExpression.class));
		assertTrue(JavaGrammarTester.valid("++1", UnaryExpression.class));
		assertTrue(JavaGrammarTester.valid("--1", UnaryExpression.class));

		assertTrue(JavaGrammarTester.valid("+a", UnaryExpression.class));
		assertTrue(JavaGrammarTester.valid("-a", UnaryExpression.class));
		assertTrue(JavaGrammarTester.valid("++a", UnaryExpression.class));
		assertTrue(JavaGrammarTester.valid("--a", UnaryExpression.class));

		assertTrue(JavaGrammarTester.valid("a", UnaryExpression.class));
	}

}
