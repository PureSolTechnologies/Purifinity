package com.puresol.uhura.grammar.production;

import static org.junit.Assert.*;

import org.junit.Test;

public class TerminalTest {

	@Test
	public void testInstance() {
		assertNotNull(new Terminal("NAME"));
		assertNotNull(new Terminal("NAME", "TEXT"));
	}

	@Test
	public void testDefaultValues() {
		Construction terminal = new Terminal("NAME");
		assertEquals("NAME", terminal.getName());
		assertEquals("", terminal.getText());
		assertFalse(terminal.isNonTerminal());
		assertTrue(terminal.isTerminal());
		assertEquals("NAME: '' (TERMINAL)", terminal.toString());
		assertEquals("NAME", terminal.toShortString());
	}

	@Test
	public void testDefaultValues2() {
		Construction terminal = new Terminal("NAME", "TEXT");
		assertEquals("NAME", terminal.getName());
		assertEquals("TEXT", terminal.getText());
		assertFalse(terminal.isNonTerminal());
		assertTrue(terminal.isTerminal());
		assertEquals("NAME: 'TEXT' (TERMINAL)", terminal.toString());
		assertEquals("NAME", terminal.toShortString());
	}

}
