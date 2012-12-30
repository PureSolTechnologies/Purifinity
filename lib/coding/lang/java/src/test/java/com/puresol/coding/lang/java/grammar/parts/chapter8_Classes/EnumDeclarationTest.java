package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class EnumDeclarationTest {

    @Test
    public void testWithoutInitializer() throws Exception {
	assertTrue(JavaGrammarPartTester.test("EnumDeclaration",
		"    public enum State {\n" + "SAME,\n" + "NEW,\n"
			+ "DELETED\n" + "}\n"));
    }
}
