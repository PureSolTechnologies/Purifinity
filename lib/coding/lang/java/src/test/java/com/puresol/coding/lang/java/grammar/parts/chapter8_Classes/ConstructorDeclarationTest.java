package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class ConstructorDeclarationTest {

    @Test
    public void testWithoutParameters() throws Exception {
	assertTrue(JavaGrammarPartTester.test("ConstructorDeclaration",
		"public Test() {\n" + "a = 0;\n" + "}\n"));
    }

    @Test
    public void testWithParameters() throws Exception {
	assertTrue(JavaGrammarPartTester.test("ConstructorDeclaration",
		"public Test(int b) {\n" + "a = b;\n" + "}\n"));
    }

}
