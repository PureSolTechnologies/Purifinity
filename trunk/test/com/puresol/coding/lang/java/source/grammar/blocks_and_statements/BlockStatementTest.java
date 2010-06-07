package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class BlockStatementTest extends TestCase {

	@Test
	public void testValids() {
		assertTrue(JavaGrammarTester
				.valid(
						"DefaultPreConditioner conditioner = new DefaultPreConditioner("
								+ "new File(\"test\"),"
								+ " Files.classToRelativePackagePath(JavaParserTest.class));",
						BlockStatement.class));
		assertTrue(JavaGrammarTester
				.valid(
						"logger.debug(\"Creating instance for class '\" + clazz.getName() + \"'\");",
						BlockStatement.class));
		assertTrue(JavaGrammarTester
				.valid(
						"Certificate[][] certsToRemove = new Certificate[selectedRows.length][];",
						BlockStatement.class));
		assertTrue(JavaGrammarTester.valid("l <<= 4;", BlockStatement.class));
		assertTrue(JavaGrammarTester
				.valid(
						"Graph.DFSTraversalVisitor visitor = graph.new DFSTraversalVisitor() { public void test() {} };",
						BlockStatement.class));

	}
}
