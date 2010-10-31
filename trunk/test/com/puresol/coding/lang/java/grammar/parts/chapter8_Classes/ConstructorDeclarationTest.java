package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class ConstructorDeclarationTest {

	@Test
	public void testWithoutParameters() {
		assertTrue(GrammarPartTester.test("ConstructorDeclaration",
				"public Test() {\n" + "a = 0;\n" + "}\n"));
	}

	@Test
	public void testWithParameters() {
		assertTrue(GrammarPartTester.test("ConstructorDeclaration",
				"public Test(int b) {\n" + "a = b;\n" + "}\n"));
	}

}
