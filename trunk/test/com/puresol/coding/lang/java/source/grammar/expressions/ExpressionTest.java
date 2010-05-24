package com.puresol.coding.lang.java.source.grammar.expressions;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;
import com.puresol.coding.lang.java.source.grammar.expressions.Expression;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ExpressionTest extends TestCase {

	@Test
	public void testAssignment() {
		Assert
				.assertTrue(JavaGrammarTester.valid("a = null",
						Expression.class));
		Assert.assertTrue(JavaGrammarTester.valid("null", Expression.class));
		Assert.assertTrue(JavaGrammarTester.valid("new Locale[0]",
				Expression.class));
		assertTrue(JavaGrammarTester.valid(
				"(args.length == 0) || (args.length > 1)", Expression.class));
		Assert.assertTrue(JavaGrammarTester.valid(
				"a = 1.2 * ( 1 + 2 ) / 2 - (1 / 2)", Expression.class));
	}

}
