package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class StatementTest extends TestCase {

    @Test
    public void testPrimary() {
	Assert.assertTrue(JavaGrammarTester
		.valid("a.b().c();", Statement.class));
    }

    @Test
    public void testVariableAssignment() {
	Assert
		.assertTrue(JavaGrammarTester.valid("a = null;",
			Statement.class));
    }

}
