package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class FieldDeclarationTest {

	@Test
	public void testWithoutInitializer() {
		assertTrue(JavaGrammarPartTester.test("FieldDeclaration", "int a;"));
	}

	@Test
	public void testWithInitializer() {
		assertTrue(JavaGrammarPartTester.test("FieldDeclaration", "int a = 0;"));
	}
}
