package com.puresol.coding.lang.java.source.grammar.expressions;

import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

public class MultiplicativeExpressionTest extends TestCase {

	@Test
	public void testValids() {
		assertTrue(JavaGrammarTester.valid("a * b",
				MultiplicativeExpression.class));
		assertTrue(JavaGrammarTester.valid("a / b",
				MultiplicativeExpression.class));
		assertTrue(JavaGrammarTester.valid("a % b",
				MultiplicativeExpression.class));
	}

}
