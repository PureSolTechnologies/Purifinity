package com.puresol.coding.lang.java.grammar.parts.chapter4_TypesValuesAndVariables;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class FloatingPointTypeIT {

    @Test
    public void testFloat() throws Exception {
	assertTrue(JavaGrammarPartTester.test("FloatingPointType", "float"));
    }

    @Test
    public void testDouble() throws Exception {
	assertTrue(JavaGrammarPartTester.test("FloatingPointType", "double"));
    }

}
