package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class VariableDeclaratorsTest {

    @Test
    public void test() throws Exception {
	assertTrue(JavaGrammarPartTester.test("VariableDeclarators", "a = 1"));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(JavaGrammarPartTester.test("VariableDeclarators",
		"a = 1, b = 2"));
    }
}
