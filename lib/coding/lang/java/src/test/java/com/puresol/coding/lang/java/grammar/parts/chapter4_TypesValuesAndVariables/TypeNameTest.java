package com.puresol.coding.lang.java.grammar.parts.chapter4_TypesValuesAndVariables;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class TypeNameTest {

    @Test
    public void testWithoutDot() throws Exception {
	assertTrue(JavaGrammarPartTester.test("TypeName", "Identifier"));
    }

    @Test
    public void testWithDots() throws Exception {
	assertTrue(JavaGrammarPartTester.test("TypeName",
		"Identifier.Identifiert2.Identifier3"));
    }
}
