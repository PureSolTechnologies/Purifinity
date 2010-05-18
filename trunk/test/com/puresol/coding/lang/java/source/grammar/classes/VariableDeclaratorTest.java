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
	}

}
