package com.puresol.coding.lang.java.source.literals;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class IntegerLiteralTest extends TestCase {

	@Test
	public void test() {
		IntegerLiteral integerLiteral = new IntegerLiteral();
		Assert.assertTrue(integerLiteral.atStart("0"));
		Assert.assertTrue(integerLiteral.atStart("1"));
		Assert.assertTrue(integerLiteral.atStart("0x1"));
		Assert.assertTrue(integerLiteral.atStart("01"));
	}
}
