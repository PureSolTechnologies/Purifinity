package com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.chapter15_Expressions;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.JavaGrammarPartTester;

public class ClassInstanceCreationExpressionIT {

	@Test
	public void test() throws Exception {
		assertTrue(JavaGrammarPartTester.test(
				"ClassInstanceCreationExpression",
				"graph.new BFSTraversalVisitor()"));
	}
}
