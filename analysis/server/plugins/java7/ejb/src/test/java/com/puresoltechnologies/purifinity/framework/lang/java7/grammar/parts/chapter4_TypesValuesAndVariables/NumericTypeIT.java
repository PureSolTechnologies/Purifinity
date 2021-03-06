package com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.chapter4_TypesValuesAndVariables;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.JavaGrammarPartTester;

public class NumericTypeIT {

	@Test
	public void testByte() throws Exception {
		assertTrue(JavaGrammarPartTester.test("NumericType", "byte"));
	}

	@Test
	public void testShort() throws Exception {
		assertTrue(JavaGrammarPartTester.test("NumericType", "short"));
	}

	@Test
	public void testInt() throws Exception {
		assertTrue(JavaGrammarPartTester.test("NumericType", "int"));
	}

	@Test
	public void testLong() throws Exception {
		assertTrue(JavaGrammarPartTester.test("NumericType", "long"));
	}

	@Test
	public void testChar() throws Exception {
		assertTrue(JavaGrammarPartTester.test("NumericType", "char"));
	}

	@Test
	public void testFloat() throws Exception {
		assertTrue(JavaGrammarPartTester.test("NumericType", "float"));
	}

	@Test
	public void testDouble() throws Exception {
		assertTrue(JavaGrammarPartTester.test("NumericType", "double"));
	}
}
