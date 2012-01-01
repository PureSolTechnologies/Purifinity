package com.puresol.coding.lang.java.grammar.parts.chapter7_Packages;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class SingleTypeImportDeclarationTest {

	@Test
	public void test() {
		assertTrue(JavaGrammarPartTester.test("SingleTypeImportDeclaration", "import TypeName ;"));
	}
}
