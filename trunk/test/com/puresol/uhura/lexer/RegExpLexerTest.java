package com.puresol.uhura.lexer;

import java.io.StringReader;
import java.util.Properties;

import org.junit.Test;

import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;

import junit.framework.TestCase;

public class RegExpLexerTest extends TestCase {

	@Test
	public void test() {
		try {
			TokenDefinitionSet rules = new TokenDefinitionSet();
			rules.addDefinition(new TokenDefinition("NUMBER", "[0-9]+"));
			rules.addDefinition(new TokenDefinition("WHITESPACE", "[ \\t]+"));
			Lexer lexer = new RegExpLexer(new Properties());
			lexer.scan(new StringReader(
					"0 1\t2 \t3 \t4 \t5\t 6 7 8 9 10 11 12 13 14 15"), rules);
			TokenStream tokenStream = lexer.getTokenStream();
			assertNotNull(tokenStream);
			assertEquals(31, tokenStream.size());
			assertEquals("NUMBER", tokenStream.get(0).getName());
			assertEquals("0", tokenStream.get(0).getText());
			assertEquals("WHITESPACE", tokenStream.get(1).getName());
			assertEquals(" ", tokenStream.get(1).getText());
			assertEquals("NUMBER", tokenStream.get(2).getName());
			assertEquals("1", tokenStream.get(2).getText());
			assertEquals("WHITESPACE", tokenStream.get(3).getName());
			assertEquals("\t", tokenStream.get(3).getText());
			assertEquals("NUMBER", tokenStream.get(4).getName());
			assertEquals("2", tokenStream.get(4).getText());
			assertEquals("WHITESPACE", tokenStream.get(5).getName());
			assertEquals(" \t", tokenStream.get(5).getText());
		} catch (LexerException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	public static void main(String args[]) {
		try {
			TokenDefinitionSet rules = new TokenDefinitionSet();
			rules.addDefinition(new TokenDefinition("NUMBER", "[0-9]+"));
			rules.addDefinition(new TokenDefinition("WHITESPACE", "[ \\t]+"));
			Lexer lexer = new RegExpLexer(new Properties());
			lexer.scan(new StringReader(
					"0 1\t2 \t3 \t4 \t5\t 6 7 8 9 10 11 12 13 14 15"), rules);
			lexer.getMetaInformation().println();
		} catch (LexerException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
