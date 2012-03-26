package com.puresol.uhura.grammar;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.Properties;

import org.junit.Test;

import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;

public class GrammarTest {

    @Test
    public void testInstance() {
	try {
	    assertNotNull(new Grammar(new Properties(),
		    new TokenDefinitionSet(), new ProductionSet()));
	} catch (GrammarException e) {
	    e.printStackTrace();
	    fail("No exception was expected!");
	}
    }

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
