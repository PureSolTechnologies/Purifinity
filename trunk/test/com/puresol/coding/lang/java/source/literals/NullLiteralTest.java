package com.puresol.coding.lang.java.source.literals;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class NullLiteralTest extends TestCase {

	@Test
	public void test() {
		NullLiteral stringLiteral = new NullLiteral();
		Assert.assertTrue(stringLiteral.atStart("null"));
	}
}
