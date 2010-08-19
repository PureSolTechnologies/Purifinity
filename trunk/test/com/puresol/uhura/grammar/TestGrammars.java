package com.puresol.uhura.grammar;

import junit.framework.Assert;

import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionConstruction;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.production.TokenConstruction;

public class TestGrammars {

	public static Grammar getTestGrammarFromLR1Pamphlet() {
		try {
			ProductionSet productions = new ProductionSet();

			Production production = new Production("Z");
			production.addElement(new ProductionConstruction("S"));
			productions.addRule(production);

			production = new Production("S");
			production.addElement(new ProductionConstruction("S"));
			production.addElement(new TokenConstruction("b"));
			productions.addRule(production);

			production = new Production("S");
			production.addElement(new TokenConstruction("b"));
			production.addElement(new ProductionConstruction("A"));
			production.addElement(new TokenConstruction("a"));
			productions.addRule(production);

			production = new Production("A");
			production.addElement(new TokenConstruction("a"));
			production.addElement(new ProductionConstruction("S"));
			production.addElement(new TokenConstruction("c"));
			productions.addRule(production);

			production = new Production("A");
			production.addElement(new TokenConstruction("a"));
			productions.addRule(production);

			production = new Production("A");
			production.addElement(new TokenConstruction("a"));
			production.addElement(new ProductionConstruction("S"));
			production.addElement(new TokenConstruction("b"));
			productions.addRule(production);

			Grammar grammar = new Grammar();
			grammar.setProductions(productions);
			return grammar;
		} catch (GrammarException e) {
			Assert.fail("No exception was expected!");
			return null;
		}
	}

}
