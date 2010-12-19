package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class ThrowsTest {

	@Test
	public void testSingleException() {
		assertTrue(JavaGrammarPartTester.test("Throws", "throws Exception"));
	}

	@Test
	public void testMultipleException() {
		assertTrue(JavaGrammarPartTester.test("Throws",
				"throws Exception1, Exception2, Exception3"));
	}
}
