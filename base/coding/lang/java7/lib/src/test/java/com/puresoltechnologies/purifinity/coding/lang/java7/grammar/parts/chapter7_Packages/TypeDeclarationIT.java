package com.puresoltechnologies.purifinity.coding.lang.java7.grammar.parts.chapter7_Packages;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.coding.lang.java7.grammar.JavaGrammarPartTester;

public class TypeDeclarationIT {

	@Test
	public void testSingleSemicolon() throws Exception {
		assertTrue(JavaGrammarPartTester.test("TypeDeclaration", ";"));
	}
}
