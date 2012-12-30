package com.puresol.coding.lang.java.grammar.parts.chapter15_Expressions;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class PrimaryTest {

    @Test
    public void testConstants() throws Exception {
	assertTrue(JavaGrammarPartTester.test("Primary", "this"));
    }
}
