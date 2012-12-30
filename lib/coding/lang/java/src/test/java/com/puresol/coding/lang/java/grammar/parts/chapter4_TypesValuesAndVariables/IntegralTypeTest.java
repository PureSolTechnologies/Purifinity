package com.puresol.coding.lang.java.grammar.parts.chapter4_TypesValuesAndVariables;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class IntegralTypeTest {

    @Test
    public void testByte() throws Exception {
	assertTrue(JavaGrammarPartTester.test("IntegralType", "byte"));
    }

    @Test
    public void testShort() throws Exception {
	assertTrue(JavaGrammarPartTester.test("IntegralType", "short"));
    }

    @Test
    public void testInt() throws Exception {
	assertTrue(JavaGrammarPartTester.test("IntegralType", "int"));
    }

    @Test
    public void testLong() throws Exception {
	assertTrue(JavaGrammarPartTester.test("IntegralType", "long"));
    }

    @Test
    public void testChar() throws Exception {
	assertTrue(JavaGrammarPartTester.test("IntegralType", "char"));
    }

}
