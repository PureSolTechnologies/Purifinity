package com.puresol.coding.lang.java.grammar.parts.chapter4_TypesValuesAndVariables;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class IntegralTypeTest {

	@Test
	public void testByte() {
		assertTrue(GrammarPartTester.test("IntegralType", "byte"));
	}

	@Test
	public void testShort() {
		assertTrue(GrammarPartTester.test("IntegralType", "short"));
	}

	@Test
	public void testInt() {
		assertTrue(GrammarPartTester.test("IntegralType", "int"));
	}

	@Test
	public void testLong() {
		assertTrue(GrammarPartTester.test("IntegralType", "long"));
	}

	@Test
	public void testChar() {
		assertTrue(GrammarPartTester.test("IntegralType", "char"));
	}

}
