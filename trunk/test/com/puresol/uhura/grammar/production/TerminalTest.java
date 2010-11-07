package com.puresol.uhura.grammar.production;

import static org.junit.Assert.*;

import org.junit.Test;

public class TerminalTest {

	@Test
	public void testInstance() {
		assertNotNull(new Terminal("NAME"));
	}

	@Test
	public void testInitialValues() {
		Construction terminal = new Terminal("NAME");
		assertEquals("NAME", terminal.getName());
		assertFalse(terminal.isNonTerminal());
		assertTrue(terminal.isTerminal());
		assertEquals("NAME: (TERMINAL)", terminal.toString());
		assertEquals("NAME", terminal.toShortString());
	}

}
