package com.puresol.coding.lang.java.grammar.parts.chapter4_TypesValuesAndVariables;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class PrimitiveTypeTest {

	@Test
	public void testByte() {
		assertTrue(JavaGrammarPartTester.test("PrimitiveType", "byte"));
	}

	@Test
	public void testShort() {
		assertTrue(JavaGrammarPartTester.test("PrimitiveType", "short"));
	}

	@Test
	public void testInt() {
		assertTrue(JavaGrammarPartTester.test("PrimitiveType", "int"));
	}

	@Test
	public void testLong() {
		assertTrue(JavaGrammarPartTester.test("PrimitiveType", "long"));
	}

	@Test
	public void testChar() {
		assertTrue(JavaGrammarPartTester.test("PrimitiveType", "char"));
	}

	@Test
	public void testFloat() {
		assertTrue(JavaGrammarPartTester.test("PrimitiveType", "float"));
	}

	@Test
	public void testDouble() {
		assertTrue(JavaGrammarPartTester.test("PrimitiveType", "double"));
	}

	@Test
	public void testBoolean() {
		assertTrue(JavaGrammarPartTester.test("PrimitiveType", "boolean"));
	}
}
