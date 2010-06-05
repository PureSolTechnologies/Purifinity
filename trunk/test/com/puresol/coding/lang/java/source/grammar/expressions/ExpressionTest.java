package com.puresol.coding.lang.java.source.grammar.expressions;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;
import com.puresol.coding.lang.java.source.grammar.expressions.Expression;

import junit.framework.TestCase;

public class ExpressionTest extends TestCase {

	@Test
	public void testAssignment() {
		assertTrue(JavaGrammarTester.valid("a = null", Expression.class));
		assertTrue(JavaGrammarTester.valid("null", Expression.class));
		assertTrue(JavaGrammarTester.valid("new Locale[0]", Expression.class));
		assertTrue(JavaGrammarTester.valid(
				"(args.length == 0) || (args.length > 1)", Expression.class));
		assertTrue(JavaGrammarTester.valid("a = 1.2 * ( 1 + 2 ) / 2 - (1 / 2)",
				Expression.class));
		assertTrue(JavaGrammarTester.valid(
				"p1 = translateMantisProperties(_props)",
				Expression.class));
	}

}
