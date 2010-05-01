package com.puresol.coding.lang.java.source.literals;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class FloatingPointLiteralTest extends TestCase {

	@Test
	public void test() {
		FloatingPointLiteral floatingPointLiteral = new FloatingPointLiteral();
		Assert.assertTrue(floatingPointLiteral.atStart("1.2345 "));
		Assert.assertTrue(floatingPointLiteral.atStart("1.e+1 "));
		Assert.assertTrue(floatingPointLiteral.atStart("1.e-1 "));
		Assert.assertTrue(floatingPointLiteral.atStart(".1e+1 "));
		Assert.assertTrue(floatingPointLiteral.atStart(".1e-1 "));
	}
}
