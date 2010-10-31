package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class VariableDeclaratorTest {

	@Test
	public void test() {
		assertTrue(GrammarPartTester.test("VariableDeclarator", "a = 1"));
	}
}
