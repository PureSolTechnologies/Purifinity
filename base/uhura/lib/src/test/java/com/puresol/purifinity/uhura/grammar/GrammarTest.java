package com.puresol.purifinity.uhura.grammar;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.Properties;

import org.junit.Test;

import com.puresol.purifinity.uhura.grammar.Grammar;
import com.puresol.purifinity.uhura.grammar.GrammarException;
import com.puresol.purifinity.uhura.grammar.production.ProductionSet;
import com.puresol.purifinity.uhura.grammar.token.TokenDefinitionSet;
import com.puresol.purifinity.uhura.lexer.RegExpLexer;
import com.puresol.purifinity.uhura.parser.lr.LR0Parser;

public class GrammarTest {

	@Test(expected = GrammarException.class)
	public void testInvalidInstance() throws GrammarException {
		assertNotNull(new Grammar(new Properties(), new TokenDefinitionSet(),
				new ProductionSet()));
	}

	@Test
	public void testInstance() throws GrammarException {
		Properties options = new Properties();
		options.put("lexer", RegExpLexer.class.getName());
		options.put("parser", LR0Parser.class.getName());
		options.put("grammar.checks", "false");
		assertNotNull(new Grammar(options, new TokenDefinitionSet(),
				new ProductionSet()));
	}

	@Test
	public void testSettersAndGetters() throws GrammarException {
		Properties options = new Properties();
		options.put("lexer", RegExpLexer.class.getName());
		options.put("parser", LR0Parser.class.getName());
		options.put("grammar.checks", "false");
		TokenDefinitionSet tokenDefinitions = new TokenDefinitionSet();
		ProductionSet productions = new ProductionSet();

		Grammar grammar = new Grammar(options, tokenDefinitions, productions);
		assertSame(options, grammar.getOptions());
		assertSame(tokenDefinitions, grammar.getTokenDefinitions());
		assertSame(productions, grammar.getProductions());
	}

}
