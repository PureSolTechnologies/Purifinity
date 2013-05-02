package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class LocalVariableDeclarationIT {

    @Test
    public void test() throws Exception {
	assertTrue(JavaGrammarPartTester.test("LocalVariableDeclaration",
		"int a = 1"));
    }
}
