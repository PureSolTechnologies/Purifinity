package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class NormalClassDeclarationTest {

	@Test
	public void testEmptyClass() {
		assertTrue(JavaGrammarPartTester.test("NormalClassDeclaration",
				"class Class { }"));
	}

	@Test
	public void testPublicEmptyClass() {
		assertTrue(JavaGrammarPartTester.test("NormalClassDeclaration",
				"public class Class { }"));
	}

	@Test
	public void testProtectedEmptyClass() {
		assertTrue(JavaGrammarPartTester.test("NormalClassDeclaration",
				"protected class Class { }"));
	}

	@Test
	public void testPrivateEmptyClass() {
		assertTrue(JavaGrammarPartTester.test("NormalClassDeclaration",
				"private class Class { }"));
	}
}
