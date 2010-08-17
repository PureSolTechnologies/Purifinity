package com.puresol.uhura.grammar;

import java.util.Properties;

import org.junit.Test;

import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;

import junit.framework.TestCase;

public class GrammarTest extends TestCase {

	@Test
	public void testSettersAndGetters() {
		Grammar grammar = new Grammar();

		Properties options = new Properties();
		grammar.setOptions(options);
		assertSame(options, grammar.getOptions());

		TokenDefinitionSet tokenDefinitions = new TokenDefinitionSet();
		grammar.setTokenDefinitions(tokenDefinitions);
		assertSame(tokenDefinitions, grammar.getTokenDefinitions());

		ProductionSet productions = new ProductionSet();
		grammar.setProductions(productions);
		assertSame(productions, grammar.getProductions());

	}
}
