package com.puresol.coding.lang.java.grammar.parts.chapter15_Expressions;

import static org.junit.Assert.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class LiteralTest {

	@Test
	public void testIntegers() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("Literal", "0"));
		assertTrue(GrammarPartTester.test("Literal", "1"));
		assertTrue(GrammarPartTester.test("Literal", "1234567890l"));
	}

	@Test
	public void testFloatingPoint() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("Literal", "0.0"));
		assertTrue(GrammarPartTester.test("Literal", "1.0"));
		assertTrue(GrammarPartTester.test("Literal", "1.2e+34"));
		assertTrue(GrammarPartTester.test("Literal", "1.2e-34"));
	}
}
