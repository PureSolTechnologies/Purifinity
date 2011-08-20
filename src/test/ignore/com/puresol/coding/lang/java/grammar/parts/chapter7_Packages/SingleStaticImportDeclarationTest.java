package com.puresol.coding.lang.java.grammar.parts.chapter7_Packages;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class SingleStaticImportDeclarationTest {

	@Test
	public void test() {
		assertTrue(JavaGrammarPartTester.test("SingleStaticImportDeclaration",
				"import static TypeName . Identifier;"));
	}
}
