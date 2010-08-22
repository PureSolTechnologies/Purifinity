package com.puresol.uhura.grammar;

import junit.framework.Assert;

import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionConstruction;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.production.TextConstruction;
import com.puresol.uhura.grammar.production.TokenConstruction;
import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;
import com.puresol.uhura.grammar.token.Visibility;

public class TestGrammars {

	public static Grammar getTestGrammarFromLR1Pamphlet() {
		try {
			TokenDefinitionSet tokenDefinitions = new TokenDefinitionSet();

			// tokenDefinitions.addDefinition(new TokenDefinition("id",
			// "[0-9.]", Visibility.VISIBLE));
			// tokenDefinitions.addDefinition(new TokenDefinition("PLUS", "+",
			// Visibility.VISIBLE));
			// tokenDefinitions.addDefinition(new TokenDefinition("STAR", "*",
			// Visibility.VISIBLE));
			// tokenDefinitions.addDefinition(new TokenDefinition("LPAREN", "(",
			// Visibility.VISIBLE));
			// tokenDefinitions.addDefinition(new TokenDefinition("RPAREN", ")",
			// Visibility.VISIBLE));
			// tokenDefinitions.addDefinition(new TokenDefinition("WHITESPACE",
			// ")", Visibility.VISIBLE));

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
			grammar.setTokenDefinitions(tokenDefinitions);
			grammar.setProductions(productions);
			return grammar;
		} catch (GrammarException e) {
			Assert.fail("No exception was expected!");
			return null;
		}
	}

	public static Grammar getTestGrammarFromDragonBook() {
		try {
			TokenDefinitionSet tokenDefinitions = new TokenDefinitionSet();

			tokenDefinitions.addDefinition(new TokenDefinition("id", "[0-9.]+",
					Visibility.VISIBLE));
			tokenDefinitions.addDefinition(new TokenDefinition("PLUS", "\\+",
					Visibility.VISIBLE));
			tokenDefinitions.addDefinition(new TokenDefinition("STAR", "\\*",
					Visibility.VISIBLE));
			tokenDefinitions.addDefinition(new TokenDefinition("LPAREN", "\\(",
					Visibility.VISIBLE));
			tokenDefinitions.addDefinition(new TokenDefinition("RPAREN", "\\)",
					Visibility.VISIBLE));
			tokenDefinitions.addDefinition(new TokenDefinition("WHITESPACE",
					"[\\s]+", Visibility.VISIBLE));

			ProductionSet productions = new ProductionSet();

			// TODO!!!
			Production production = new Production("Z");
			production.addElement(new ProductionConstruction("E"));
			productions.addRule(production);

			production = new Production("E");
			production.addElement(new ProductionConstruction("E"));
			production.addElement(new TextConstruction("+"));
			production.addElement(new ProductionConstruction("T"));
			productions.addRule(production);

			// production = new Production("E");
			// production.addElement(new ProductionConstruction("E"));
			// production.addElement(new TextConstruction("-"));
			// production.addElement(new ProductionConstruction("T"));
			// productions.addRule(production);

			production = new Production("E");
			production.addElement(new ProductionConstruction("T"));
			productions.addRule(production);

			production = new Production("T");
			production.addElement(new ProductionConstruction("T"));
			production.addElement(new TextConstruction("*"));
			production.addElement(new ProductionConstruction("F"));
			productions.addRule(production);

			// production = new Production("T");
			// production.addElement(new ProductionConstruction("T"));
			// production.addElement(new TextConstruction("/"));
			// production.addElement(new ProductionConstruction("F"));
			// productions.addRule(production);

			production = new Production("T");
			production.addElement(new ProductionConstruction("F"));
			productions.addRule(production);

			production = new Production("F");
			production.addElement(new TextConstruction("("));
			production.addElement(new ProductionConstruction("E"));
			production.addElement(new TextConstruction(")"));
			productions.addRule(production);

			production = new Production("F");
			production.addElement(new TokenConstruction("id"));
			productions.addRule(production);

			Grammar grammar = new Grammar();
			grammar.setTokenDefinitions(tokenDefinitions);
			grammar.setProductions(productions);
			return grammar;
		} catch (GrammarException e) {
			Assert.fail("No exception was expected!");
			return null;
		}
	}
}
