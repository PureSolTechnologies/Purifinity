package com.puresol.coding.lang.java.source.grammar.types_values_variables;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class WildcardTest extends TestCase {

	@Test
	public void testSingleQuestionMark() {
		assertTrue(JavaGrammarTester.valid("?", Wildcard.class));
	}

	@Test
	public void testExtends() {
		assertTrue(JavaGrammarTester.valid("? extends Type", Wildcard.class));
	}

	@Test
	public void testSuper() {
		assertTrue(JavaGrammarTester.valid("? super Type", Wildcard.class));
	}
}
