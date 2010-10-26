package com.puresol.coding.lang.java.grammar.parts.chapter4_TypesValuesAndVariables;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class TypeNameTest {

	@Test
	public void testWithoutDot() {
		assertTrue(GrammarPartTester.test("TypeName", "Identifier"));
	}

	@Test
	public void testWithDots() {
		assertTrue(GrammarPartTester.test("TypeName",
				"Identifier.Identifiert2.Identifier3"));
	}
}
