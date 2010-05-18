package com.puresol.coding.lang.java.source.grammar.classes;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class ArgumentsTest extends TestCase {

	@Test
	public void testValids() {
		assertTrue(JavaGrammarTester.valid("()", Arguments.class));
		assertTrue(JavaGrammarTester.valid("(a, b)", Arguments.class));
		assertTrue(JavaGrammarTester.valid("(a, b, c)", Arguments.class));
		assertTrue(JavaGrammarTester.valid("(a, 1.2345, \"string\")",
				Arguments.class));
		assertTrue(JavaGrammarTester.valid("(new Object())", Arguments.class));
	}
}
