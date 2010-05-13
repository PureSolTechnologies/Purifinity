package com.puresol.coding.lang.java.source.grammar.types_values_variables;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class QualifiedNameTest extends TestCase {

	@Test
	public void testValidNames() {
		Assert.assertTrue(JavaGrammarTester.valid("a", QualifiedName.class));
		Assert.assertTrue(JavaGrammarTester.valid("a.b", QualifiedName.class));
		Assert
				.assertTrue(JavaGrammarTester.valid("a.b.c",
						QualifiedName.class));
	}

	@Test
	public void testInvalidNames() {
		// invalid due to unexpected end of token stream:
		Assert.assertTrue(JavaGrammarTester.invalid("a.", QualifiedName.class));
	}
}
