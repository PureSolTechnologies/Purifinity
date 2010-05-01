package com.puresol.coding.lang.java.source.literals;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class StringLiteralTest extends TestCase {

	@Test
	public void test() {
		StringLiteral stringLiteral = new StringLiteral();
		Assert.assertTrue(stringLiteral.atStart("\"\""));
		Assert.assertTrue(stringLiteral.atStart("\"String\""));
		Assert.assertTrue(stringLiteral.atStart("\"String: \\\"\"Hallo\\\""));
	}
}
