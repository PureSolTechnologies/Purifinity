package com.puresol.coding.lang.java.grammar.parts.chapter15_Expressions;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class AssignmentTest {

	@Test
	public void testConstants() {
		assertTrue(GrammarPartTester.test("Assignment", "a = b"));
	}

	@Test
	public void testNullAssignment() {
		assertTrue(GrammarPartTester.test("Assignment", "a = null"));
	}

	@Test
	public void testMethodResultAssignment() {
		assertTrue(GrammarPartTester.test("Assignment",
				"s = Globals.class.getResourceAsStream(\"/build.id\")"));
	}

}
