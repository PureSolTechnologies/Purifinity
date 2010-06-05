package com.puresol.coding.lang.java.source.literals;

import org.junit.Test;

import junit.framework.TestCase;

public class IdentifierTest extends TestCase {

	@Test
	public void testValids() {
		Identifier identifier = new Identifier();
		assertTrue(identifier.atStart("identifier"));
		assertTrue(identifier.atStart("_identifier"));
	}

	@Test
	public void testInvalids() {
		Identifier identifier = new Identifier();
		assertFalse(identifier.atStart("123"));
	}

}
