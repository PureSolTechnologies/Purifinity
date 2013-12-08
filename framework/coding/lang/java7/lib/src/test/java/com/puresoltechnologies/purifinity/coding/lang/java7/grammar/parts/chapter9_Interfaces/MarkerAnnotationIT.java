package com.puresoltechnologies.purifinity.coding.lang.java7.grammar.parts.chapter9_Interfaces;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.coding.lang.java7.grammar.JavaGrammarPartTester;

public class MarkerAnnotationIT {

	@Test
	public void test() throws Exception {
		assertTrue(JavaGrammarPartTester.test("MarkerAnnotation", "@TypeName"));
	}
}
