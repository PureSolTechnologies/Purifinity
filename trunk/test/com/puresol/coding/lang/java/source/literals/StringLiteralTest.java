package com.puresol.coding.lang.java.source.literals;

import org.junit.Test;

import junit.framework.TestCase;

public class StringLiteralTest extends TestCase {

	@Test
	public void test() {
		StringLiteral stringLiteral = new StringLiteral();
		assertTrue(stringLiteral.atStart("\"\""));
		assertTrue(stringLiteral.atStart("\"String\""));
		assertTrue(stringLiteral.atStart("\"String: \\\"\"Hallo\\\""));
		assertTrue(stringLiteral.included("AA \"Test\" AA"));
		assertEquals("\"Test\"", stringLiteral
				.getIncludedToken("AA \"Test\" AA"));
	}
}
