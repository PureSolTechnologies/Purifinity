package com.puresol.coding.lang.java.source.grammar.expressions;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class ParExpressionTest extends TestCase {

	@Test
	public void testValids() {
		assertTrue(JavaGrammarTester.valid("(args.length > 1)",
				ParExpression.class));
		assertTrue(JavaGrammarTester.valid("(args.length == 0)",
				ParExpression.class));
		assertTrue(JavaGrammarTester.valid(
				"((args.length == 0) || (args.length > 1))",
				ParExpression.class));
	}

}
