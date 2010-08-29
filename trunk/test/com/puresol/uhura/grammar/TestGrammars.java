package com.puresol.uhura.grammar;

import java.util.Properties;

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

			tokenDefinitions.addDefinition(new TokenDefinition("a", "a",
					Visibility.VISIBLE));
			tokenDefinitions.addDefinition(new TokenDefinition("b", "b",
					Visibility.VISIBLE));
			tokenDefinitions.addDefinition(new TokenDefinition("c", "c",
					Visibility.VISIBLE));

			ProductionSet productions = new ProductionSet();

			Production production = new Production("Z");
			production.addConstruction(new ProductionConstruction("S"));
			productions.add(production);

			production = new Production("S");
			production.addConstruction(new ProductionConstruction("S"));
			production.addConstruction(new TokenConstruction("b"));
			productions.add(production);

			production = new Production("S");
			production.addConstruction(new TokenConstruction("b"));
			production.addConstruction(new ProductionConstruction("A"));
			production.addConstruction(new TokenConstruction("a"));
			productions.add(production);

			production = new Production("A");
			production.addConstruction(new TokenConstruction("a"));
			production.addConstruction(new ProductionConstruction("S"));
			production.addConstruction(new TokenConstruction("c"));
			productions.add(production);

			production = new Production("A");
			production.addConstruction(new TokenConstruction("a"));
			productions.add(production);

			production = new Production("A");
			production.addConstruction(new TokenConstruction("a"));
			production.addConstruction(new ProductionConstruction("S"));
			production.addConstruction(new TokenConstruction("b"));
			productions.add(production);

			return new Grammar(new Properties(), tokenDefinitions, productions);
		} catch (GrammarException e) {
			e.printStackTrace();
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
			production.addConstruction(new ProductionConstruction("E"));
			productions.add(production);

			production = new Production("E");
			production.addConstruction(new ProductionConstruction("E"));
			production.addConstruction(new TextConstruction("+"));
			production.addConstruction(new ProductionConstruction("T"));
			productions.add(production);

			// production = new Production("E");
			// production.addElement(new ProductionConstruction("E"));
			// production.addElement(new TextConstruction("-"));
			// production.addElement(new ProductionConstruction("T"));
			// productions.addRule(production);

			production = new Production("E");
			production.addConstruction(new ProductionConstruction("T"));
			productions.add(production);

			production = new Production("T");
			production.addConstruction(new ProductionConstruction("T"));
			production.addConstruction(new TextConstruction("*"));
			production.addConstruction(new ProductionConstruction("F"));
			productions.add(production);

			// production = new Production("T");
			// production.addElement(new ProductionConstruction("T"));
			// production.addElement(new TextConstruction("/"));
			// production.addElement(new ProductionConstruction("F"));
			// productions.addRule(production);

			production = new Production("T");
			production.addConstruction(new ProductionConstruction("F"));
			productions.add(production);

			production = new Production("F");
			production.addConstruction(new TextConstruction("("));
			production.addConstruction(new ProductionConstruction("E"));
			production.addConstruction(new TextConstruction(")"));
			productions.add(production);

			production = new Production("F");
			production.addConstruction(new TokenConstruction("id"));
			productions.add(production);

			return new Grammar(new Properties(), tokenDefinitions, productions);
		} catch (GrammarException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
			return null;
		}
	}

	public static Grammar getLR1TestGrammarFromDragonBook() {
		try {
			TokenDefinitionSet tokenDefinitions = new TokenDefinitionSet();

			tokenDefinitions.addDefinition(new TokenDefinition("c", "c",
					Visibility.VISIBLE));
			tokenDefinitions.addDefinition(new TokenDefinition("d", "d",
					Visibility.VISIBLE));

			ProductionSet productions = new ProductionSet();

			Production production = new Production("Z");
			production.addConstruction(new ProductionConstruction("S"));
			productions.add(production);

			production = new Production("S");
			production.addConstruction(new ProductionConstruction("C"));
			production.addConstruction(new ProductionConstruction("C"));
			productions.add(production);

			production = new Production("C");
			production.addConstruction(new TokenConstruction("c"));
			production.addConstruction(new ProductionConstruction("C"));
			productions.add(production);

			production = new Production("C");
			production.addConstruction(new TokenConstruction("d"));
			productions.add(production);
			return new Grammar(new Properties(), tokenDefinitions, productions);
		} catch (GrammarException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
			return null;
		}
	}

	public static Grammar getTestLLGrammarFromDragonBook() {
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

			Production production = new Production("E");
			production.addConstruction(new ProductionConstruction("T"));
			production.addConstruction(new ProductionConstruction("E'"));
			productions.add(production);

			production = new Production("E'");
			production.addConstruction(new TextConstruction("+"));
			production.addConstruction(new ProductionConstruction("T"));
			production.addConstruction(new ProductionConstruction("E'"));
			productions.add(production);

			production = new Production("E'");
			productions.add(production);

			production = new Production("T");
			production.addConstruction(new ProductionConstruction("F"));
			production.addConstruction(new ProductionConstruction("T'"));
			productions.add(production);

			production = new Production("T'");
			production.addConstruction(new TextConstruction("*"));
			production.addConstruction(new ProductionConstruction("F"));
			production.addConstruction(new ProductionConstruction("T'"));
			productions.add(production);

			production = new Production("T'");
			productions.add(production);

			production = new Production("F");
			production.addConstruction(new TextConstruction("("));
			production.addConstruction(new ProductionConstruction("E"));
			production.addConstruction(new TextConstruction(")"));
			productions.add(production);

			production = new Production("F");
			production.addConstruction(new TokenConstruction("id"));
			productions.add(production);

			return new Grammar(new Properties(), tokenDefinitions, productions);
		} catch (GrammarException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
			return null;
		}
	}
}
