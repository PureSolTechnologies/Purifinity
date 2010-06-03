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

	}

}
