package com.puresol.purifinity.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.java.grammar.JavaGrammarPartTester;

public class ExceptionTypeIT {

    @Test
    public void testSingleException() throws Exception {
	assertTrue(JavaGrammarPartTester.test("ExceptionType", "Exception"));
    }

}
