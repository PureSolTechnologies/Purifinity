package com.puresoltechnologies.purifinity.coding.lang.java7.grammar.parts.chapter15_Expressions;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.coding.lang.java7.grammar.JavaGrammarPartTester;

public class AssignmentIT {

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
