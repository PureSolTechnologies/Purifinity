package com.puresol.coding.lang.java.grammar.parts.chapter7_Packages;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class TypeImportOnDemandDeclarationTest {

	@Test
	public void test() {
		assertTrue(JavaGrammarPartTester.test("TypeImportOnDemandDeclaration",
				"import java.util.*;"));
	}
}
