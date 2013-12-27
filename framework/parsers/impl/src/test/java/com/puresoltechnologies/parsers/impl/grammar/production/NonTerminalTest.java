package com.puresoltechnologies.parsers.impl.grammar.production;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresoltechnologies.parsers.impl.grammar.production.Construction;
import com.puresoltechnologies.parsers.impl.grammar.production.NonTerminal;

public class NonTerminalTest {

	@Test
	public void testInstance() {
		assertNotNull(new NonTerminal("NAME"));
	}

	@Test
	public void testInitialValues() {
		Construction nonTerminal = new NonTerminal("NAME");
		assertEquals("NAME", nonTerminal.getName());
		assertTrue(nonTerminal.isNonTerminal());
		assertFalse(nonTerminal.isTerminal());
		assertEquals("NAME: (NON-TERMINAL)", nonTerminal.toString());
		assertEquals("NAME", nonTerminal.toShortString());
	}

}