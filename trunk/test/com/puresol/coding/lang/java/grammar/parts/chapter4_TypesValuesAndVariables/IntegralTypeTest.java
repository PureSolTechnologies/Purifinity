package com.puresol.coding.lang.java.grammar.parts.chapter4_TypesValuesAndVariables;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class IntegralTypeTest {

	@Test
	public void testByte() {
		assertTrue(JavaGrammarPartTester.test("IntegralType", "byte"));
	}

	@Test
	public void testShort() {
		assertTrue(JavaGrammarPartTester.test("IntegralType", "short"));
	}

	@Test
	public void testInt() {
		assertTrue(JavaGrammarPartTester.test("IntegralType", "int"));
	}

	@Test
	public void testLong() {
		assertTrue(JavaGrammarPartTester.test("IntegralType", "long"));
	}

	@Test
	public void testChar() {
		assertTrue(JavaGrammarPartTester.test("IntegralType", "char"));
	}

}
