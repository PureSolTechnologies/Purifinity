package com.puresol.coding.lang.java.grammar.parts.chapter15_Expressions;

import static org.junit.Assert.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class AssignmentTest {

	@Test
	public void testConstants() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("Assignment", "a = b"));
	}

	@Test
	public void testNullAssignment() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("Assignment", "a = null"));
	}

	@Test
	public void testMethodResultAssignment() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("Assignment",
				"s = Globals.class.getResourceAsStream(\"/build.id\")"));
	}

	@Test
	public void testPrimaryNewAssignment() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("Assignment",
				"traverser = graph.new BFSTraversalVisitor()"));
	}
}
