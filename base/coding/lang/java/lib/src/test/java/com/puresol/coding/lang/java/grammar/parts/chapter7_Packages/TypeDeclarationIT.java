package com.puresol.coding.lang.java.grammar.parts.chapter7_Packages;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class TypeDeclarationIT {

    @Test
    public void testSingleSemicolon() throws Exception {
	assertTrue(JavaGrammarPartTester.test("TypeDeclaration", ";"));
    }
}
