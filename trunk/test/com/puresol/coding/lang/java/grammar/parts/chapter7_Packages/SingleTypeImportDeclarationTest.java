package com.puresol.coding.lang.java.grammar.parts.chapter7_Packages;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class SingleTypeImportDeclarationTest {

	@Test
	public void test() {
		assertTrue(GrammarPartTester.test("SingleTypeImportDeclaration", "import TypeName ;"));
	}
}
