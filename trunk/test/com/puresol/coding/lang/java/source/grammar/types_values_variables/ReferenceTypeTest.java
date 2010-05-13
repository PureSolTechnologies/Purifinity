package com.puresol.coding.lang.java.source.grammar.types_values_variables;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ReferenceTypeTest extends TestCase {

	@Test
	public void testValidVariableTypes() {
		Assert.assertTrue(JavaGrammarTester.valid("Test", ReferenceType.class));
		Assert.assertTrue(JavaGrammarTester.valid("Test.test",
				ReferenceType.class));
		Assert.assertTrue(JavaGrammarTester.valid("Test.test[]",
				ReferenceType.class));
	}

	@Test
	public void testInvalidVariableTypes() {
	}
}
