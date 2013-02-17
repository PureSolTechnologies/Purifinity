package com.puresol.coding.lang.java.grammar.parts.chapter15_Expressions;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class AssignmentTest {

    @Test
    public void testConstants() throws Exception {
	assertTrue(JavaGrammarPartTester.test("Assignment", "a = b"));
    }

    @Test
    public void testNullAssignment() throws Exception {
	assertTrue(JavaGrammarPartTester.test("Assignment", "a = null"));
    }

    @Test
    public void testMethodResultAssignment() throws Exception {
	assertTrue(JavaGrammarPartTester.test("Assignment",
		"s = Globals.class.getResourceAsStream(\"/build.id\")"));
    }

    @Test
    public void testPrimaryNewAssignment() throws Exception {
	assertTrue(JavaGrammarPartTester.test("Assignment",
		"traverser = graph.new BFSTraversalVisitor()"));
    }
}