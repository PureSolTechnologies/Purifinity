package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class FieldDeclarationTest {

	@Test
	public void testWithoutInitializer() {
		assertTrue(GrammarPartTester.test("FieldDeclaration", "int a;"));
	}

	@Test
	public void testWithInitializer() {
		assertTrue(GrammarPartTester.test("FieldDeclaration", "int a = 0;"));
	}
}
