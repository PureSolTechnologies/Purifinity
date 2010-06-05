package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class BlockTest extends TestCase {

	@Test
	public void testValids() {
		assertTrue(JavaGrammarTester.valid("{" + "showUsage();" + "return;"
				+ "}", Block.class));
		assertTrue(JavaGrammarTester.valid("{"
				+ "this.i18nDirectory = args[0];" + "}", Block.class));
		assertTrue(JavaGrammarTester.valid("{"
				+ "if ((args.length == 0) || (args.length > 1)) {"
				+ "showUsage();" + "return;" + "}"
				+ "this.i18nDirectory = args[0];" + "}", Block.class));
		assertTrue(JavaGrammarTester
				.valid(
						"{"
								+ "logger.debug(\"Creating instance for class '\" + clazz.getName() + \"'\");"
								+ "return new Translator(clazz);" + "}",
						Block.class));
		assertTrue(JavaGrammarTester.valid("{"
				+ "Properties p1 = translateMantisProperties(_props);" + "}",
				Block.class));
	}
}
