package com.puresol.coding.lang.java.grammar.parts.chapter9_Interfaces;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class MarkerAnnotationTest {

	@Test
	public void test() {
		assertTrue(GrammarPartTester.test("MarkerAnnotation", "@TypeName"));
	}
}
