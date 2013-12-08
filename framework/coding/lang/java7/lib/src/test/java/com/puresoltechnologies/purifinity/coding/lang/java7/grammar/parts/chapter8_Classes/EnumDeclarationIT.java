package com.puresoltechnologies.purifinity.coding.lang.java7.grammar.parts.chapter8_Classes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.coding.lang.java7.grammar.JavaGrammarPartTester;

public class EnumDeclarationIT {

	@Test
	public void testWithoutInitializer() throws Exception {
		assertTrue(JavaGrammarPartTester.test("EnumDeclaration",
				"    public enum State {\n" + "SAME,\n" + "NEW,\n"
						+ "DELETED\n" + "}\n"));
	}
}
