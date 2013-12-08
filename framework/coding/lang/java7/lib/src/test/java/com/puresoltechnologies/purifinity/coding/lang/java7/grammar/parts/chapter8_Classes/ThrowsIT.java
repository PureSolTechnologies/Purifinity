package com.puresoltechnologies.purifinity.coding.lang.java7.grammar.parts.chapter8_Classes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.coding.lang.java7.grammar.JavaGrammarPartTester;

public class ThrowsIT {

	@Test
	public void testSingleException() throws Exception {
		assertTrue(JavaGrammarPartTester.test("Throws", "throws Exception"));
	}

	@Test
	public void testMultipleException() throws Exception {
		assertTrue(JavaGrammarPartTester.test("Throws",
				"throws Exception1, Exception2, Exception3"));
	}
}
