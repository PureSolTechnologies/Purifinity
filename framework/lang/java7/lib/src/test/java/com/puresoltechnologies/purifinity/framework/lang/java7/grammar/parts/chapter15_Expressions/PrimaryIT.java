package com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.chapter15_Expressions;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.JavaGrammarPartTester;

public class PrimaryIT {

	@Test
	public void testConstants() throws Exception {
		assertTrue(JavaGrammarPartTester.test("Primary", "this"));
	}
}
