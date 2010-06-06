package com.puresol.coding.lang.java.source.grammar.arrays;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class ArrayInitializerTest extends TestCase {

	@Test
	public void testValids() {
		assertTrue(JavaGrammarTester.valid("{}", ArrayInitializer.class));
		assertTrue(JavaGrammarTester.valid("{1,2}", ArrayInitializer.class));
		assertTrue(JavaGrammarTester.valid("{1,2,}", ArrayInitializer.class));
		assertTrue(JavaGrammarTester.valid("{\n" + "\"1\", \"2\", \"3\",\n"
				+ "}", ArrayInitializer.class));
	}
}
