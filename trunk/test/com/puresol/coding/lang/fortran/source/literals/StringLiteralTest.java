package com.puresol.coding.lang.fortran.source.literals;

import junit.framework.Assert;

import org.junit.Test;

public class StringLiteralTest {

	@Test
	public void testDoubleQuote() {
		StringLiteral literal = new StringLiteral();
		Assert.assertTrue(literal.atStart("\"Test\""));
	}

	@Test
	public void testSingleQuote() {
		StringLiteral literal = new StringLiteral();
		Assert.assertTrue(literal.atStart("'Test'"));
	}
	
}
