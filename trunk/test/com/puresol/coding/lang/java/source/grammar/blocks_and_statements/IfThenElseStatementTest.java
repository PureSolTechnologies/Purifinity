package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class IfThenElseStatementTest extends TestCase {

	@Test
	public void testValids() {
		assertTrue(JavaGrammarTester.valid(
				"if ((args.length == 0) || (args.length > 1)) {" + "}",
				IfThenElseStatement.class));
		assertTrue(JavaGrammarTester.valid(
				"if (ver == null || ver.compareTo(\"1.5.0\") < 0) {"
						+ "Properties p1 = translateMantisProperties(_props);"
						+ "}", IfThenElseStatement.class));
	}
}
