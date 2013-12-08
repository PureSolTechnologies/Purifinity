package com.puresoltechnologies.purifinity.coding.lang.java7.grammar.parts.chapter8_Classes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.coding.lang.java7.grammar.JavaGrammarPartTester;

public class ConstructorBodyIT {

	@Test
	public void testSingleException() throws Exception {
		assertTrue(JavaGrammarPartTester.test("ConstructorBody", "{\n"
				+ "a = b;\n" + "}\n"));
	}

}
