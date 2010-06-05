package com.puresol.coding.lang.java.source.grammar.classes;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class FieldDeclarationTest extends TestCase {

	@Test
	public void testValids() {
		assertTrue(JavaGrammarTester.valid(
				"private static Locale[] additionalLocales = new Locale[0];",
				FieldDeclaration.class));
		assertTrue(JavaGrammarTester.valid(
				"private static final String defaults [] = {\n"
						+ "\"1\", \"2\", \"3\"\n" + "};",
				FieldDeclaration.class));
		assertTrue(JavaGrammarTester.valid(
				"private static final String defaults [] = {\n"
						+ "\"1\", \"2\", \"3\",\n" + "};",
				FieldDeclaration.class));
	}
}
