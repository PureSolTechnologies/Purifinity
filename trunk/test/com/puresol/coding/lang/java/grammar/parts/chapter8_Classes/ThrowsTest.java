package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class ThrowsTest {

	@Test
	public void testSingleException() {
		assertTrue(GrammarPartTester.test("Throws", "throws Exception"));
	}

	@Test
	public void testMultipleException() {
		assertTrue(GrammarPartTester.test("Throws",
				"throws Exception1, Exception2, Exception3"));
	}
}
