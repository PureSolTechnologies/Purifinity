package com.puresol.coding.lang.java.source.grammar.classes;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class MethodDeclarationTest extends TestCase {

	@Test
	public void testValids() {
		Logger.getRootLogger().setLevel(Level.DEBUG);
		assertTrue(JavaGrammarTester
				.valid(
						"public static Translator newInstance(Class<?> clazz) {"
								+ "logger.debug(\"Creating instance for class '\" + clazz.getName() + \"'\");"
								+ "return new Translator(clazz);" + "}",
						MethodDeclaration.class));

		assertTrue(JavaGrammarTester.valid(
				"public I18NRelease(String args[]) {"
						+ "if ((args.length == 0) || (args.length > 1)) {"
						+ "showUsage();" + "return;" + "}"
						+ "this.i18nDirectory = args[0];" + "}",
				MethodDeclaration.class));

	}

}
