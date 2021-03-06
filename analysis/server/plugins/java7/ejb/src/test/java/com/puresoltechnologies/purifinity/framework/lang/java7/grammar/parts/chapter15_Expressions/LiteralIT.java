package com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.chapter15_Expressions;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.JavaGrammarPartTester;

public class LiteralIT {

	@Test
	public void testIntegers() throws Exception {
		assertTrue(JavaGrammarPartTester.test("Literal", "0"));
		assertTrue(JavaGrammarPartTester.test("Literal", "1"));
		assertTrue(JavaGrammarPartTester.test("Literal", "1234567890l"));
	}

	@Test
	public void testFloatingPoint() throws Exception {
		assertTrue(JavaGrammarPartTester.test("Literal", "0.0"));
		assertTrue(JavaGrammarPartTester.test("Literal", "1.0"));
		assertTrue(JavaGrammarPartTester.test("Literal", "1.2e+34"));
		assertTrue(JavaGrammarPartTester.test("Literal", "1.2e-34"));
	}
}
