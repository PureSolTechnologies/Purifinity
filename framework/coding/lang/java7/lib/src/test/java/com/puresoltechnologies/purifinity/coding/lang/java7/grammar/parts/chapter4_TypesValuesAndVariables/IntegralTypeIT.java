package com.puresoltechnologies.purifinity.coding.lang.java7.grammar.parts.chapter4_TypesValuesAndVariables;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.coding.lang.java7.grammar.JavaGrammarPartTester;

public class IntegralTypeIT {

	@Test
	public void testByte() throws Exception {
		assertTrue(JavaGrammarPartTester.test("IntegralType", "byte"));
	}

	@Test
	public void testShort() throws Exception {
		assertTrue(JavaGrammarPartTester.test("IntegralType", "short"));
	}

	@Test
	public void testInt() throws Exception {
		assertTrue(JavaGrammarPartTester.test("IntegralType", "int"));
	}

	@Test
	public void testLong() throws Exception {
		assertTrue(JavaGrammarPartTester.test("IntegralType", "long"));
	}

	@Test
	public void testChar() throws Exception {
		assertTrue(JavaGrammarPartTester.test("IntegralType", "char"));
	}

}
