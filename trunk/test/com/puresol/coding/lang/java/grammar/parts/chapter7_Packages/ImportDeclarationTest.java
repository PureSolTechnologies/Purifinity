package com.puresol.coding.lang.java.grammar.parts.chapter7_Packages;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class ImportDeclarationTest {

	@Test
	public void testSingleTypeImportDeclaration() {
		assertTrue(JavaGrammarPartTester.test("ImportDeclaration",
				"import TypeName ;"));
	}

	@Test
	public void testTypeImportOnDemandDeclaration() {
		assertTrue(JavaGrammarPartTester.test("ImportDeclaration",
				"import java.util.*;"));
	}

	@Test
	public void testSingleStaticImportDeclaration() {
		assertTrue(JavaGrammarPartTester.test("ImportDeclaration",
				"import static TypeName . Identifier;"));
	}

	@Test
	public void testStaticImportOnDemandDeclaration() {
		assertTrue(JavaGrammarPartTester.test("ImportDeclaration",
				"import static TypeName . * ;"));
	}

}
