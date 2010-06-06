package com.puresol.coding.lang.java.source.grammar.classes;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class VariableInitializerTest extends TestCase {

	@Test
	public void testValids() {
		assertTrue(JavaGrammarTester.valid(
				"new Certificate[selectedRows.length][]",
				VariableInitializer.class));
	}

}
