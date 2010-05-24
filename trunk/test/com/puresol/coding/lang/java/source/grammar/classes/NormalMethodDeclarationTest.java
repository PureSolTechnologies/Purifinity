package com.puresol.coding.lang.java.source.grammar.classes;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class NormalMethodDeclarationTest extends TestCase {

	@Test
	public void testValids() {
		assertTrue(JavaGrammarTester
				.valid(
						"public static Translator newInstance(Class<?> clazz) {"
								+ "logger.debug(\"Creating instance for class '\" + clazz.getName() + \"'\");"
								+ "return new Translator(clazz);" + "}",
						NormalMethodDeclaration.class));

		assertTrue(JavaGrammarTester.valid(
				"static public void setAdditionalLocales(Locale... additionalLocales) {"
						+ "}", NormalMethodDeclaration.class));

	}

}
