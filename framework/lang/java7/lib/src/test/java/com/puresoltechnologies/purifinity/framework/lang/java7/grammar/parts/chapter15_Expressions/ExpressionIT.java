package com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.chapter15_Expressions;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.JavaGrammarPartTester;

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
