package com.puresoltechnologies.purifinity.coding.lang.java7.grammar.parts.chapter4_TypesValuesAndVariables;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.coding.lang.java7.grammar.JavaGrammarPartTester;

public class FloatingPointTypeIT {

	@Test
	public void testFloat() throws Exception {
		assertTrue(JavaGrammarPartTester.test("FloatingPointType", "float"));
	}

	@Test
	public void testDouble() throws Exception {
		assertTrue(JavaGrammarPartTester.test("FloatingPointType", "double"));
	}

}
