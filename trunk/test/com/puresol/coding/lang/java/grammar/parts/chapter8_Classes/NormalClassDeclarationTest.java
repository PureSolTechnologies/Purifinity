package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class NormalClassDeclarationTest {

	@Test
	public void testEmptyClass() {
		assertTrue(GrammarPartTester.test("NormalClassDeclaration",
				"class Class { }"));
	}

	@Test
	public void testPublicEmptyClass() {
		assertTrue(GrammarPartTester.test("NormalClassDeclaration",
				"public class Class { }"));
	}

	@Test
	public void testProtectedEmptyClass() {
		assertTrue(GrammarPartTester.test("NormalClassDeclaration",
				"protected class Class { }"));
	}

	@Test
	public void testPrivateEmptyClass() {
		assertTrue(GrammarPartTester.test("NormalClassDeclaration",
				"private class Class { }"));
	}
}
