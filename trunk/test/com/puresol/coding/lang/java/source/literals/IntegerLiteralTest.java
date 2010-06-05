package com.puresol.coding.lang.java.source.literals;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class IntegerLiteralTest extends TestCase {

	@Test
	public void test() {
		IntegerLiteral integerLiteral = new IntegerLiteral();
		Assert.assertEquals("0",integerLiteral.getTokenAtStart("0"));
		Assert.assertEquals("1",integerLiteral.getTokenAtStart("1"));
		Assert.assertEquals("0x1", integerLiteral.getTokenAtStart("0x1"));
		Assert.assertEquals("01",integerLiteral.getTokenAtStart("01"));
	}
}
