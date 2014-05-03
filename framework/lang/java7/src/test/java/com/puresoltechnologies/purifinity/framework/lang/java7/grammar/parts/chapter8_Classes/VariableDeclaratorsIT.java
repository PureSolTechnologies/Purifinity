package com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.chapter8_Classes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.JavaGrammarPartTester;

public class VariableDeclaratorsIT {

	@Test
	public void test() throws Exception {
		assertTrue(JavaGrammarPartTester.test("VariableDeclarators", "a = 1"));
	}

	@Test
	public void test2() throws Exception {
		assertTrue(JavaGrammarPartTester.test("VariableDeclarators",
				"a = 1, b = 2"));
	}
}
