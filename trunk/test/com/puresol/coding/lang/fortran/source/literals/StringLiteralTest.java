package com.puresol.coding.lang.fortran.source.literals;

import junit.framework.Assert;

import org.junit.Test;

public class StringLiteralTest {

	@Test
	public void testDoubleQuote() {
		CharLiteral literal = new CharLiteral();
		Assert.assertTrue(literal.atStart("\"Test\""));
	}

	@Test
	public void testSingleQuote() {
		CharLiteral literal = new CharLiteral();
		Assert.assertTrue(literal.atStart("'Test'"));
	}
	
}
