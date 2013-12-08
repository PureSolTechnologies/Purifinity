package com.puresoltechnologies.purifinity.coding.lang.java7.grammar.parts.chapter8_Classes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.coding.lang.java7.grammar.JavaGrammarPartTester;

public class VariableDeclaratorIT {

	@Test
	public void test() throws Exception {
		assertTrue(JavaGrammarPartTester.test("VariableDeclarator", "a = 1"));
	}
}
