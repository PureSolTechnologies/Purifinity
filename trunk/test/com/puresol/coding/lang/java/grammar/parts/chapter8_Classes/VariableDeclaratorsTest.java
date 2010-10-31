package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class VariableDeclaratorsTest {

	@Test
	public void test() {
		assertTrue(GrammarPartTester.test("VariableDeclarators", "a = 1"));
	}

	@Test
	public void test2() {
		assertTrue(GrammarPartTester
				.test("VariableDeclarators", "a = 1, b = 2"));
	}
}
