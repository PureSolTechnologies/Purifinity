package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class VariableDeclaratorsTest {

	@Test
	public void test() {
		assertTrue(JavaGrammarPartTester.test("VariableDeclarators", "a = 1"));
	}

	@Test
	public void test2() {
		assertTrue(JavaGrammarPartTester
				.test("VariableDeclarators", "a = 1, b = 2"));
	}
}
