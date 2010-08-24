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
			production.addElement(new ProductionConstruction("S"));
			productions.addRule(production);

			production = new Production("S");
			production.addElement(new ProductionConstruction("C"));
			production.addElement(new ProductionConstruction("C"));
			productions.addRule(production);

			production = new Production("C");
			production.addElement(new TokenConstruction("c"));
			production.addElement(new ProductionConstruction("C"));
			productions.addRule(production);

			production = new Production("C");
			production.addElement(new TokenConstruction("d"));
			productions.addRule(production);
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

			/*
			 * Here is not production 'Z' included! In LL grammars an explicit
			 * start element is not needed.
			 */

			Production production = new Production("E");
			production.addElement(new ProductionConstruction("T"));
			production.addElement(new ProductionConstruction("E'"));
			productions.addRule(production);

			production = new Production("E'");
			production.addElement(new TextConstruction("+"));
			production.addElement(new ProductionConstruction("T"));
			production.addElement(new ProductionConstruction("E'"));
			productions.addRule(production);

			production = new Production("E'");
			productions.addRule(production);

			production = new Production("T");
			production.addElement(new ProductionConstruction("F"));
			production.addElement(new ProductionConstruction("T'"));
			productions.addRule(production);

			production = new Production("T'");
			production.addElement(new TextConstruction("*"));
			production.addElement(new ProductionConstruction("F"));
			production.addElement(new ProductionConstruction("T'"));
			productions.addRule(production);

			production = new Production("T'");
			productions.addRule(production);

			production = new Production("F");
			production.addElement(new TextConstruction("("));
			production.addElement(new ProductionConstruction("E"));
			production.addElement(new TextConstruction(")"));
			productions.addRule(production);

			production = new Production("F");
			production.addElement(new TokenConstruction("id"));
			productions.addRule(production);

			return new Grammar(new Properties(), tokenDefinitions, productions);
		} catch (GrammarException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
			return null;
		}
	}
}
