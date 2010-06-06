package com.puresol.coding.lang.java.source.grammar.expressions;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class PrimaryTest extends TestCase {

	@Test
	public void testValids() {
		assertTrue(JavaGrammarTester.valid(
				"JNLPClassLoader.super.getResource(name)", Primary.class));
	}
}
