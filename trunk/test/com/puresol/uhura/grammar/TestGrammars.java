package com.puresol.uhura.grammar;

import java.util.Properties;

import junit.framework.Assert;

import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.NonTerminal;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.production.Terminal;
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
			production.addConstruction(new NonTerminal("S"));
			productions.add(production);

			production = new Production("S");
			production.addConstruction(new NonTerminal("S"));
			production.addConstruction(new Terminal("b"));
			productions.add(production);

			production = new Production("S");
			production.addConstruction(new Terminal("b"));
			production.addConstruction(new NonTerminal("A"));
			production.addConstruction(new Terminal("a"));
			productions.add(production);

			production = new Production("A");
			production.addConstruction(new Terminal("a"));
			production.addConstruction(new NonTerminal("S"));
			production.addConstruction(new Terminal("c"));
			productions.add(production);

			production = new Production("A");
			production.addConstruction(new Terminal("a"));
			productions.add(production);

			production = new Production("A");
			production.addConstruction(new Terminal("a"));
			production.addConstruction(new NonTerminal("S"));
			production.addConstruction(new Terminal("b"));
			productions.add(production);

			return new Grammar(new Properties(), tokenDefinitions, productions);
		} catch (GrammarException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
			return null;
		}
	}

	public static Grammar getSLR1TestGrammarFromDragonBook() {
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
			production.addConstruction(new NonTerminal("E"));
			productions.add(production);

			production = new Production("E");
			production.addConstruction(new NonTerminal("E"));
			production.addConstruction(new Terminal("PLUS"));
			production.addConstruction(new NonTerminal("T"));
			productions.add(production);

			// production = new Production("E");
			// production.addElement(new ProductionConstruction("E"));
			// production.addElement(new TextConstruction("-"));
			// production.addElement(new ProductionConstruction("T"));
			// productions.addRule(production);

			production = new Production("E");
			production.addConstruction(new NonTerminal("T"));
			productions.add(production);

			production = new Production("T");
			production.addConstruction(new NonTerminal("T"));
			production.addConstruction(new Terminal("STAR"));
			production.addConstruction(new NonTerminal("F"));
			productions.add(production);

			// production = new Production("T");
			// production.addElement(new ProductionConstruction("T"));
			// production.addElement(new TextConstruction("/"));
			// production.addElement(new ProductionConstruction("F"));
			// productions.addRule(production);

			production = new Production("T");
			production.addConstruction(new NonTerminal("F"));
			productions.add(production);

			production = new Production("F");
			production.addConstruction(new Terminal("LPAREN"));
			production.addConstruction(new NonTerminal("E"));
			production.addConstruction(new Terminal("RPAREN"));
			productions.add(production);

			production = new Production("F");
			production.addConstruction(new Terminal("id"));
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
			production.addConstruction(new NonTerminal("S"));
			productions.add(production);

			production = new Production("S");
			production.addConstruction(new NonTerminal("C"));
			production.addConstruction(new NonTerminal("C"));
			productions.add(production);

			production = new Production("C");
			production.addConstruction(new Terminal("c"));
			production.addConstruction(new NonTerminal("C"));
			productions.add(production);

			production = new Production("C");
			production.addConstruction(new Terminal("d"));
			productions.add(production);
			return new Grammar(new Properties(), tokenDefinitions, productions);
		} catch (GrammarException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
			return null;
		}
	}

	public static Grammar getLALR1TestGrammarFromDragonBook() {
		try {
			TokenDefinitionSet tokenDefinitions = new TokenDefinitionSet();

			tokenDefinitions.addDefinition(new TokenDefinition("id", "id",
					Visibility.VISIBLE));
			tokenDefinitions.addDefinition(new TokenDefinition("EQUALS", "=",
					Visibility.VISIBLE));
			tokenDefinitions.addDefinition(new TokenDefinition("STAR", "*",
					Visibility.VISIBLE));

			ProductionSet productions = new ProductionSet();

			Production production = new Production("Z");
			production.addConstruction(new NonTerminal("S"));
			productions.add(production);

			production = new Production("S");
			production.addConstruction(new NonTerminal("L"));
			production.addConstruction(new Terminal("EQUALS", "="));
			production.addConstruction(new NonTerminal("R"));
			productions.add(production);

			production = new Production("S");
			production.addConstruction(new NonTerminal("R"));
			productions.add(production);

			production = new Production("L");
			production.addConstruction(new Terminal("STAR", "*"));
			production.addConstruction(new NonTerminal("R"));
			productions.add(production);

			production = new Production("L");
			production.addConstruction(new Terminal("id", "id"));
			productions.add(production);

			production = new Production("R");
			production.addConstruction(new NonTerminal("L"));
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
			production.addConstruction(new NonTerminal("T"));
			production.addConstruction(new NonTerminal("E'"));
			productions.add(production);

			production = new Production("E'");
			production.addConstruction(new Terminal("PLUS"));
			production.addConstruction(new NonTerminal("T"));
			production.addConstruction(new NonTerminal("E'"));
			productions.add(production);

			production = new Production("E'");
			productions.add(production);

			production = new Production("T");
			production.addConstruction(new NonTerminal("F"));
			production.addConstruction(new NonTerminal("T'"));
			productions.add(production);

			production = new Production("T'");
			production.addConstruction(new Terminal("STAR"));
			production.addConstruction(new NonTerminal("F"));
			production.addConstruction(new NonTerminal("T'"));
			productions.add(production);

			production = new Production("T'");
			productions.add(production);

			production = new Production("F");
			production.addConstruction(new Terminal("LPAREN"));
			production.addConstruction(new NonTerminal("E"));
			production.addConstruction(new Terminal("RPAREN"));
			productions.add(production);

			production = new Production("F");
			production.addConstruction(new Terminal("id"));
			productions.add(production);

			return new Grammar(new Properties(), tokenDefinitions, productions);
		} catch (GrammarException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
			return null;
		}
	}
}
