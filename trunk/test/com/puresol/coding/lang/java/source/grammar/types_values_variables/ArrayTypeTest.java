package com.puresol.coding.lang.java.source.grammar.types_values_variables;

import junit.framework.TestCase;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

public class ArrayTypeTest extends TestCase {

	@Test
	public void testValids() {
		assertTrue(JavaGrammarTester.valid("a[]", ArrayType.class));
	}
}
