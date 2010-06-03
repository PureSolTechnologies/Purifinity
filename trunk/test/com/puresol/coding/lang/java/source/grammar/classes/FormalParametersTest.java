package com.puresol.coding.lang.java.source.grammar.classes;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class FormalParametersTest extends TestCase {

	@Test
	public void testValids() {
		assertTrue(JavaGrammarTester.valid("(Locale ... locales)",
				FormalParameters.class));
	}
}
