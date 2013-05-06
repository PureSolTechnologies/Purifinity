package com.puresol.coding.lang.java.grammar.parts.chapter15_Expressions;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class ClassInstanceCreationExpressionIT {

    @Test
    public void test() throws Exception {
	assertTrue(JavaGrammarPartTester.test(
		"ClassInstanceCreationExpression",
		"graph.new BFSTraversalVisitor()"));
    }
}