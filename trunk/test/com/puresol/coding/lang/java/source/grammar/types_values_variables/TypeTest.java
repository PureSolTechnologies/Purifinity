package com.puresol.coding.lang.java.source.grammar.types_values_variables;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TypeTest extends TestCase {

	@Test
	public void testPrimitive() {
		Assert.assertTrue(JavaGrammarTester.valid("int", Type.class));
		Assert.assertTrue(JavaGrammarTester.valid("Locale", Type.class));
	}
}
