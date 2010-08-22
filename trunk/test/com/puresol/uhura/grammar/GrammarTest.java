package com.puresol.uhura.grammar;

import java.util.Properties;

import org.junit.Test;

import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;

import junit.framework.TestCase;

public class GrammarTest extends TestCase {

	@Test
	public void testSettersAndGetters() {
		try {
			Properties options = new Properties();
			TokenDefinitionSet tokenDefinitions = new TokenDefinitionSet();
			ProductionSet productions = new ProductionSet();

			Grammar grammar = new Grammar(options, tokenDefinitions,
					productions);
			assertSame(options, grammar.getOptions());
			assertSame(tokenDefinitions, grammar.getTokenDefinitions());
			assertSame(productions, grammar.getProductions());
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
