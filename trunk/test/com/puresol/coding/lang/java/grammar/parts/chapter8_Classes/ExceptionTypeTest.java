package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class ExceptionTypeTest {

	@Test
	public void testSingleException() {
		assertTrue(GrammarPartTester.test("ExceptionType", "Exception"));
	}

}
