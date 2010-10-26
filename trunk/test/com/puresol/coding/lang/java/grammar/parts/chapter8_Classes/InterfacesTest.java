package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class InterfacesTest {

	@Test
	public void testSingleInterface() {
		assertTrue(GrammarPartTester.test("Interfaces", "implements Interface"));
	}

	@Test
	public void testMultipleInterfaces() {
		assertTrue(GrammarPartTester.test("Interfaces",
				"implements Interface1, Interface2, Interface3"));
	}
}
