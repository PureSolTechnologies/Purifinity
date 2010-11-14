package com.puresol.coding.lang.java.grammar.parts.chapter15_Expressions;

import static org.junit.Assert.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class ExpressionTest {

	@Test
	public void testConstants() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(JavaGrammarPartTester.test("Expression", "1"));
	}

	@Test
	public void testAssignment() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(JavaGrammarPartTester.test("Expression", "a = b"));
	}

	@Test
	public void testMethodInvocationExpression() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(JavaGrammarPartTester.test("Expression",
				"Globals.class.getResourceAsStream(\"/build.id\")"));
	}

}
