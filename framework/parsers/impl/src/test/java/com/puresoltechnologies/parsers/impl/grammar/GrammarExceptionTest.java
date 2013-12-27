package com.puresoltechnologies.parsers.impl.grammar;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresoltechnologies.parsers.impl.grammar.GrammarException;

public class GrammarExceptionTest {

	@Test
	public void testInstance() {
		assertNotNull(new GrammarException("Test message"));
	}

	@Test
	public void testInitValues() {
		GrammarException exception = new GrammarException("Test message");
		assertEquals("Test message", exception.getMessage());
	}
}