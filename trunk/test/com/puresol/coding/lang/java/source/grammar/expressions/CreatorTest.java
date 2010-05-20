package com.puresol.coding.lang.java.source.grammar.expressions;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class CreatorTest extends TestCase {

    @Test
    public void testValids() {
	assertTrue(JavaGrammarTester.valid("new Object()", Creator.class));
    }
}
