package com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.chapter8_Classes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.JavaGrammarPartTester;

public class InterfacesIT {

	@Test
	public void testSingleInterface() throws Exception {
		assertTrue(JavaGrammarPartTester.test("Interfaces",
				"implements Interface"));
	}

	@Test
	public void testMultipleInterfaces() throws Exception {
		assertTrue(JavaGrammarPartTester.test("Interfaces",
				"implements Interface1, Interface2, Interface3"));
	}
}
