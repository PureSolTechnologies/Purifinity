package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class LocalVariableDeclarationTest {

	@Test
	public void test() {
		assertTrue(JavaGrammarPartTester.test("LocalVariableDeclaration", "int a = 1"));
	}
}