package com.puresol.coding.lang.java.source.literals;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class BooleanLiteralTest extends TestCase {

	@Test
	public void test() {
		BooleanLiteral booleanLiteral = new BooleanLiteral();
		Assert.assertTrue(booleanLiteral.atStart("true"));
		Assert.assertTrue(booleanLiteral.atStart("false"));
	}
}
