package com.puresol.uhura.grammar.production;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenMetaData;
import com.puresol.uhura.source.UnspecifiedSourceCodeLocation;

public class TerminalTest {

    @Test
    public void testInstance() {
	assertNotNull(new Terminal("NAME", "name"));
    }

    @Test
    public void testInitialValues() {
	Terminal terminal = new Terminal("NAME", "name");
	assertEquals("NAME", terminal.getName());
	assertEquals("name", terminal.getText());
	assertFalse(terminal.isNonTerminal());
	assertTrue(terminal.isTerminal());
	assertEquals("NAME: (TERMINAL) 'name'", terminal.toString());
	assertEquals("NAME 'name'", terminal.toShortString());
    }

    @Test
    public void testEquals() {
	assertTrue(new Terminal("NAME", "TEXT").equals(new Terminal("NAME",
		"TEXT")));
	assertFalse(new Terminal("NAME", "TEXT").equals(new Terminal("NAME",
		"text")));
    }

    @Test
    public void testMatches() {
	Terminal terminal = new Terminal("TEST", null);
	assertTrue(terminal.matches(new Token("TEST", "anything",
		Visibility.VISIBLE, new TokenMetaData(
			new UnspecifiedSourceCodeLocation(), 1, 1))));
	terminal = new Terminal("TEST", "");
	assertTrue(terminal.matches(new Token("TEST", "anything",
		Visibility.VISIBLE, new TokenMetaData(
			new UnspecifiedSourceCodeLocation(), 1, 1))));
	terminal = new Terminal("TEST", "anything");
	assertTrue(terminal.matches(new Token("TEST", "anything",
		Visibility.VISIBLE, new TokenMetaData(
			new UnspecifiedSourceCodeLocation(), 1, 1))));
    }

}
