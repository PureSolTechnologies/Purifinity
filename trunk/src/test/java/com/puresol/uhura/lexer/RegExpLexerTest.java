package com.puresol.uhura.lexer;

import static org.junit.Assert.*;

import java.io.File;
import java.io.StringReader;
import java.util.Properties;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;

public class RegExpLexerTest {

	@Test
	public void test() throws Throwable {
		TokenDefinitionSet rules = new TokenDefinitionSet();
		rules.addDefinition(new TokenDefinition("NUMBER", "[0-9]+"));
		rules.addDefinition(new TokenDefinition("WHITESPACE", "[ \\t]+"));
		Grammar grammar = new Grammar(new Properties(), rules,
				new ProductionSet());
		Lexer lexer = new RegExpLexer(grammar);
		LexerResult result = lexer.lex(SourceCode.read(new StringReader(
				"0 1\t2 \t3 \t4 \t5\t 6 7 8 9 10 11 12 13 14 15"), new File(
				"SampleString")), "SampleString");
		TokenStream tokenStream = result.getTokenStream();
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
	}

	@Test(expected = LexerException.class)
	public void testFindNextToken() throws Throwable {
		TokenDefinitionSet rules = new TokenDefinitionSet();
		rules.addDefinition(new TokenDefinition("STRING", "\"[^\"]*\""));
		Grammar grammar = new Grammar(new Properties(), rules,
				new ProductionSet());
		Lexer lexer = new RegExpLexer(grammar);
		lexer.lex(SourceCode.read(new StringReader(
				"\"String without trailing double quote!"), new File(
				"SampleString")), "SampleString");
	}
}
