package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class ExceptionTypeTest {

    @Test
    public void testSingleException() throws Exception {
	assertTrue(JavaGrammarPartTester.test("ExceptionType", "Exception"));
    }

}
