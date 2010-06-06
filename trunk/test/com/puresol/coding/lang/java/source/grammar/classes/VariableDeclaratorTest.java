package com.puresol.coding.lang.java.source.grammar.classes;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class VariableDeclaratorTest extends TestCase {

	@Test
	public void testValids() {
		assertTrue(JavaGrammarTester.valid("a", VariableDeclarator.class));
		assertTrue(JavaGrammarTester.valid("a[]", VariableDeclarator.class));
		assertTrue(JavaGrammarTester
				.valid("a = null", VariableDeclarator.class));
		assertTrue(JavaGrammarTester.valid("additionalLocales = new Locale[0]",
				VariableDeclarator.class));
		assertTrue(JavaGrammarTester.valid("defaults [] = {\n"
				+ "\"1\", \"2\", \"3\",\n" + "}", VariableDeclarator.class));
		assertTrue(JavaGrammarTester.valid(
				"certsToRemove = new Certificate[selectedRows.length][]",
				VariableDeclarator.class));
	}

}
