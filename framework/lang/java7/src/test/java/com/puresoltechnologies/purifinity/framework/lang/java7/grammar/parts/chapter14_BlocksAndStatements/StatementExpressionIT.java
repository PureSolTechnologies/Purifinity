package com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.chapter14_BlocksAndStatements;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.JavaGrammarPartTester;

public class StatementExpressionIT {

	@Test
	public void testConstants() throws Exception {
		assertTrue(JavaGrammarPartTester.test("StatementExpression", "a = b"));
	}
}
