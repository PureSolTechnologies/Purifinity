package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class ConstructorBodyTest {

	@Test
	public void testSingleException() {
		assertTrue(JavaGrammarPartTester.test("ConstructorBody", "{\n" + "a = b;\n"
				+ "}\n"));
	}

}
