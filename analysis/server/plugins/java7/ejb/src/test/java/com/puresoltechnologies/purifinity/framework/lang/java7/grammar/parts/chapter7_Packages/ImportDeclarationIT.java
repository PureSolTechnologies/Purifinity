package com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.chapter7_Packages;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.JavaGrammarPartTester;

public class ImportDeclarationIT {

	@Test
	public void testSingleTypeImportDeclaration() throws Exception {
		assertTrue(JavaGrammarPartTester.test("ImportDeclaration",
				"import TypeName ;"));
	}

	@Test
	public void testTypeImportOnDemandDeclaration() throws Exception {
		assertTrue(JavaGrammarPartTester.test("ImportDeclaration",
				"import java.util.*;"));
	}

	@Test
	public void testSingleStaticImportDeclaration() throws Exception {
		assertTrue(JavaGrammarPartTester.test("ImportDeclaration",
				"import static TypeName . Identifier;"));
	}

	@Test
	public void testStaticImportOnDemandDeclaration() throws Exception {
		assertTrue(JavaGrammarPartTester.test("ImportDeclaration",
				"import static TypeName . * ;"));
	}

}
