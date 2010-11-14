package com.puresol.coding.lang.java.grammar.parts.chapter9_Interfaces;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class MarkerAnnotationTest {

	@Test
	public void test() {
		assertTrue(JavaGrammarPartTester.test("MarkerAnnotation", "@TypeName"));
	}
}
