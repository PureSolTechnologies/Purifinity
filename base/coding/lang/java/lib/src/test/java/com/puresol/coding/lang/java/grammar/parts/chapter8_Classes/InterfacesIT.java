package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

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
