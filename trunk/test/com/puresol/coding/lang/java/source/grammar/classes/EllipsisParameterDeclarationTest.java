package com.puresol.coding.lang.java.source.grammar.classes;

import junit.framework.TestCase;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

public class EllipsisParameterDeclarationTest extends TestCase {

	@Test
	public void testValids() {
		assertTrue(JavaGrammarTester.valid("Locale ... locales",
				EllipsisParameterDeclaration.class));
	}

}
