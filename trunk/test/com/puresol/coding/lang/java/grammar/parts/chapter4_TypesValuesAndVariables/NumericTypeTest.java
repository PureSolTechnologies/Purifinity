package com.puresol.coding.lang.java.grammar.parts.chapter4_TypesValuesAndVariables;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class NumericTypeTest {

	@Test
	public void testByte() {
		assertTrue(GrammarPartTester.test("NumericType", "byte"));
	}

	@Test
	public void testShort() {
		assertTrue(GrammarPartTester.test("NumericType", "short"));
	}

	@Test
	public void testInt() {
		assertTrue(GrammarPartTester.test("NumericType", "int"));
	}

	@Test
	public void testLong() {
		assertTrue(GrammarPartTester.test("NumericType", "long"));
	}

	@Test
	public void testChar() {
		assertTrue(GrammarPartTester.test("NumericType", "char"));
	}

	@Test
	public void testFloat() {
		assertTrue(GrammarPartTester.test("NumericType", "float"));
	}

	@Test
	public void testDouble() {
		assertTrue(GrammarPartTester.test("NumericType", "double"));
	}
}
