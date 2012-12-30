package com.puresol.coding.lang.java.grammar.parts.chapter4_TypesValuesAndVariables;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class FloatingPointTypeTest {

	@Test
	public void testFloat() {
		assertTrue(JavaGrammarPartTester.test("FloatingPointType", "float"));
	}

	@Test
	public void testDouble() {
		assertTrue(JavaGrammarPartTester.test("FloatingPointType", "double"));
	}

}