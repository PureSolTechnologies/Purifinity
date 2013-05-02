package com.puresol.coding.lang.java.grammar.parts.chapter4_TypesValuesAndVariables;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class PrimitiveTypeIT {

    @Test
    public void testByte() throws Exception {
	assertTrue(JavaGrammarPartTester.test("PrimitiveType", "byte"));
    }

    @Test
    public void testShort() throws Exception {
	assertTrue(JavaGrammarPartTester.test("PrimitiveType", "short"));
    }

    @Test
    public void testInt() throws Exception {
	assertTrue(JavaGrammarPartTester.test("PrimitiveType", "int"));
    }

    @Test
    public void testLong() throws Exception {
	assertTrue(JavaGrammarPartTester.test("PrimitiveType", "long"));
    }

    @Test
    public void testChar() throws Exception {
	assertTrue(JavaGrammarPartTester.test("PrimitiveType", "char"));
    }

    @Test
    public void testFloat() throws Exception {
	assertTrue(JavaGrammarPartTester.test("PrimitiveType", "float"));
    }

    @Test
    public void testDouble() throws Exception {
	assertTrue(JavaGrammarPartTester.test("PrimitiveType", "double"));
    }

    @Test
    public void testBoolean() throws Exception {
	assertTrue(JavaGrammarPartTester.test("PrimitiveType", "boolean"));
    }
}
