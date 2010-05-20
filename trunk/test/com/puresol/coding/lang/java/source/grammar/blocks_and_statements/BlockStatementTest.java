package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class BlockStatementTest extends TestCase {

    @Test
    public void testValids() {
	Logger.getRootLogger().setLevel(Level.DEBUG);
	assertTrue(JavaGrammarTester
		.valid(
			"DefaultPreConditioner conditioner = new DefaultPreConditioner("
				+ "new File(\"test\"),"
				+ " Files.classToRelativePackagePath(JavaParserTest.class));",
			BlockStatement.class));
    }
}
