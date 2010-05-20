package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class LocalVariableDeclarationTest extends TestCase {

    @Test
    public void testValids() {
	assertTrue(JavaGrammarTester.valid("int a",
		LocalVariableDeclaration.class));
	assertTrue(JavaGrammarTester.valid("Object a = 1.2",
		LocalVariableDeclaration.class));
	assertTrue(JavaGrammarTester.valid("Object a = null",
		LocalVariableDeclaration.class));
	assertTrue(JavaGrammarTester.valid("Object a = new Object()",
		LocalVariableDeclaration.class));
    }

}
