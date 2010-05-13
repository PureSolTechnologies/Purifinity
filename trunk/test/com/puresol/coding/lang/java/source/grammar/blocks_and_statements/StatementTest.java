package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class StatementTest extends TestCase {

	@Test
	public void testAssignment() {
		Logger.getRootLogger().setLevel(Level.DEBUG);
		Assert.assertTrue(JavaGrammarTester
				.valid("a.b().c();", Statement.class));
	}

}
