package com.puresol.coding.lang.java.grammar.parts.chapter4_TypesValuesAndVariables;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class NumericTypeTest {

	@Test
	public void testByte() {
		assertTrue(JavaGrammarPartTester.test("NumericType", "byte"));
	}

	@Test
	public void testShort() {
		assertTrue(JavaGrammarPartTester.test("NumericType", "short"));
	}

	@Test
	public void testInt() {
		assertTrue(JavaGrammarPartTester.test("NumericType", "int"));
	}

	@Test
	public void testLong() {
		assertTrue(JavaGrammarPartTester.test("NumericType", "long"));
	}

	@Test
	public void testChar() {
		assertTrue(JavaGrammarPartTester.test("NumericType", "char"));
	}

	@Test
	public void testFloat() {
		assertTrue(JavaGrammarPartTester.test("NumericType", "float"));
	}

	@Test
	public void testDouble() {
		assertTrue(JavaGrammarPartTester.test("NumericType", "double"));
	}
}
