package com.puresol.coding.lang.java.grammar.parts.chapter9_Interfaces;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class MarkerAnnotationTest {

    @Test
    public void test() throws Exception {
	assertTrue(JavaGrammarPartTester.test("MarkerAnnotation", "@TypeName"));
    }
}
