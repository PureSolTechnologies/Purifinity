package com.puresol.coding.lang.java.source.grammar.expressions;

import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

public class ConditionalOrExpressionTest extends TestCase {

	@Test
	public void testValids() {
		assertTrue(JavaGrammarTester.valid("a || b",
				ConditionalOrExpression.class));

		assertTrue(JavaGrammarTester.valid("(a) || (b)",
				ConditionalOrExpression.class));
}

}
