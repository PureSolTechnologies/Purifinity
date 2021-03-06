package com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.chapter8_Classes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.JavaGrammarPartTester;

public class NormalClassDeclarationIT {

	@Test
	public void testEmptyClass() throws Exception {
		assertTrue(JavaGrammarPartTester.test("NormalClassDeclaration",
				"class Class { }"));
	}

	@Test
	public void testPublicEmptyClass() throws Exception {
		assertTrue(JavaGrammarPartTester.test("NormalClassDeclaration",
				"public class Class { }"));
	}

	@Test
	public void testProtectedEmptyClass() throws Exception {
		assertTrue(JavaGrammarPartTester.test("NormalClassDeclaration",
				"protected class Class { }"));
	}

	@Test
	public void testPrivateEmptyClass() throws Exception {
		assertTrue(JavaGrammarPartTester.test("NormalClassDeclaration",
				"private class Class { }"));
	}
}
