package com.puresoltechnologies.purifinity.coding.lang.java7.grammar.parts.chapter8_Classes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.coding.lang.java7.grammar.JavaGrammarPartTester;

public class FieldDeclarationIT {

	@Test
	public void testWithoutInitializer() throws Exception {
		assertTrue(JavaGrammarPartTester.test("FieldDeclaration", "int a;"));
	}

	@Test
	public void testWithInitializer() throws Exception {
		assertTrue(JavaGrammarPartTester.test("FieldDeclaration", "int a = 0;"));
	}
}
