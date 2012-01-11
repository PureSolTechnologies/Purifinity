package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class ConstructorDeclarationTest {

	@Test
	public void testWithoutParameters() {
		assertTrue(JavaGrammarPartTester.test("ConstructorDeclaration",
				"public Test() {\n" + "a = 0;\n" + "}\n"));
	}

	@Test
	public void testWithParameters() {
		assertTrue(JavaGrammarPartTester.test("ConstructorDeclaration",
				"public Test(int b) {\n" + "a = b;\n" + "}\n"));
	}

}
