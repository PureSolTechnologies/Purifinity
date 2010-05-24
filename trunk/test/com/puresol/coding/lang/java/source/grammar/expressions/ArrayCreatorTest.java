package com.puresol.coding.lang.java.source.grammar.expressions;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ArrayCreatorTest extends TestCase {
	@Test
	public void testAssignment() {
		Assert.assertTrue(JavaGrammarTester.valid("new Locale[0]",
				ArrayCreator.class));
	}

}
