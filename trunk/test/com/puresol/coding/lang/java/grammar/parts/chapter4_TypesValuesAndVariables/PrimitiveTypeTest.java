package com.puresol.coding.lang.java.grammar.parts.chapter4_TypesValuesAndVariables;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class PrimitiveTypeTest {

	@Test
	public void testByte() {
		assertTrue(GrammarPartTester.test("PrimitiveType", "byte"));
	}

	@Test
	public void testShort() {
		assertTrue(GrammarPartTester.test("PrimitiveType", "short"));
	}

	@Test
	public void testInt() {
		assertTrue(GrammarPartTester.test("PrimitiveType", "int"));
	}

	@Test
	public void testLong() {
		assertTrue(GrammarPartTester.test("PrimitiveType", "long"));
	}

	@Test
	public void testChar() {
		assertTrue(GrammarPartTester.test("PrimitiveType", "char"));
	}

	@Test
	public void testFloat() {
		assertTrue(GrammarPartTester.test("PrimitiveType", "float"));
	}

	@Test
	public void testDouble() {
		assertTrue(GrammarPartTester.test("PrimitiveType", "double"));
	}

	@Test
	public void testBoolean() {
		assertTrue(GrammarPartTester.test("PrimitiveType", "boolean"));
	}
}
