package com.puresol.coding.lang.java.grammar.parts.chapter15_Expressions;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class LiteralTest {

    @Test
    public void testIntegers() throws Exception {
	assertTrue(JavaGrammarPartTester.test("Literal", "0"));
	assertTrue(JavaGrammarPartTester.test("Literal", "1"));
	assertTrue(JavaGrammarPartTester.test("Literal", "1234567890l"));
    }

    @Test
    public void testFloatingPoint() throws Exception {
	assertTrue(JavaGrammarPartTester.test("Literal", "0.0"));
	assertTrue(JavaGrammarPartTester.test("Literal", "1.0"));
	assertTrue(JavaGrammarPartTester.test("Literal", "1.2e+34"));
	assertTrue(JavaGrammarPartTester.test("Literal", "1.2e-34"));
    }
}
