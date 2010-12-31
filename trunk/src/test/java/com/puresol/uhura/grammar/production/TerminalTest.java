package com.puresol.uhura.grammar.production;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenMetaData;

public class TerminalTest {

	@Test
	public void testInstance() {
		assertNotNull(new Terminal("NAME", "name", false));
	}

	@Test
	public void testInitialValues() {
		Terminal terminal = new Terminal("NAME", "name", false);
		assertEquals("NAME", terminal.getName());
		assertEquals("name", terminal.getText());
		assertEquals(false, terminal.isIgnoreCase());
		assertFalse(terminal.isNonTerminal());
		assertTrue(terminal.isTerminal());
		assertEquals("NAME: (TERMINAL) 'name'", terminal.toString());
		assertEquals("NAME 'name'", terminal.toShortString());
	}

	@Test
	public void testMatches() {
		Terminal terminal = new Terminal("TEST", null, false);
		assertTrue(terminal
				.matches(new Token("TEST", "anything", Visibility.VISIBLE,
						new TokenMetaData("SourceName", 0, 0, 1, 1))));
		terminal = new Terminal("TEST", "", false);
		assertTrue(terminal
				.matches(new Token("TEST", "anything", Visibility.VISIBLE,
						new TokenMetaData("SourceName", 0, 0, 1, 1))));
		terminal = new Terminal("TEST", "anything", false);
		assertTrue(terminal
				.matches(new Token("TEST", "anything", Visibility.VISIBLE,
						new TokenMetaData("SourceName", 0, 0, 1, 1))));
	}
	@Test
	public void testMatchesIgnoreCase() {
		Terminal terminal = new Terminal("NAME", "text", true);
		assertTrue(terminal
				.matches(new Token("NAME", "text", Visibility.VISIBLE,
						new TokenMetaData("SourceName", 0, 0, 1, 1))));
		assertTrue(terminal
				.matches(new Token("NAME", "TEXT", Visibility.VISIBLE,
						new TokenMetaData("SourceName", 0, 0, 1, 1))));
	}
}
