package com.puresol.uhura.grammar.uhura;

import java.util.Properties;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionConstruction;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.production.TextConstruction;
import com.puresol.uhura.grammar.production.TokenConstruction;
import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;
import com.puresol.uhura.grammar.token.Visibility;

/**
 * This class contains the grammar for Nyota Uhura's grammar files. This grammar
 * is programmed due to the inability to read grammar files without a grammar.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class UhuraGrammar {

	public static Grammar getGrammar() {
		try {
			return new Grammar(new Properties(), getTokenDefinitions(),
					getProductions());
		} catch (GrammarException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	private static TokenDefinitionSet getTokenDefinitions()
			throws GrammarException {
		TokenDefinitionSet tokenDefinitions = new TokenDefinitionSet();

		tokenDefinitions.addDefinition(new TokenDefinition("COMMENT",
				UhuraTokenRegExps.COMMENT, Visibility.HIDDEN));
		tokenDefinitions.addDefinition(new TokenDefinition("WHITE_SPACE",
				UhuraTokenRegExps.WHITE_SPACE, Visibility.HIDDEN));

		tokenDefinitions.addDefinition(new TokenDefinition("EQUALS", "="));
		tokenDefinitions.addDefinition(new TokenDefinition("SEMICOLON", ";"));
		tokenDefinitions.addDefinition(new TokenDefinition("COLON", ":"));
		tokenDefinitions.addDefinition(new TokenDefinition("DOT", "\\."));
		tokenDefinitions.addDefinition(new TokenDefinition("PLUS", "\\+"));
		tokenDefinitions.addDefinition(new TokenDefinition("STAR", "\\*"));
		tokenDefinitions.addDefinition(new TokenDefinition("QUESTION_MARK",
				"\\?"));
		tokenDefinitions.addDefinition(new TokenDefinition("LPAREN", "\\("));
		tokenDefinitions.addDefinition(new TokenDefinition("RPAREN", "\\)"));
		tokenDefinitions.addDefinition(new TokenDefinition("VERTICAL_BAR",
				"\\|"));

		tokenDefinitions.addDefinition(new TokenDefinition("LEFT_BRACKET",
				"\\["));
		tokenDefinitions.addDefinition(new TokenDefinition("RIGHT_BRACKET",
				"\\]"));

		tokenDefinitions.addDefinition(new TokenDefinition(
				"LEFT_CURLY_BRACKET", "\\{"));
		tokenDefinitions.addDefinition(new TokenDefinition(
				"RIGHT_CURLY_BRACKET", "\\}"));

		tokenDefinitions
				.addDefinition(new TokenDefinition("OPTIONS", "OPTIONS"));
		tokenDefinitions.addDefinition(new TokenDefinition("HELPER", "HELPER"));
		tokenDefinitions.addDefinition(new TokenDefinition("TOKENS", "TOKENS"));
		tokenDefinitions.addDefinition(new TokenDefinition("PRODUCTIONS",
				"PRODUCTIONS"));

		tokenDefinitions.addDefinition(new TokenDefinition("STRING_LITERAL",
				UhuraTokenRegExps.STRING_LITERAL));
		tokenDefinitions.addDefinition(new TokenDefinition("INTEGER_LITERAL",
				UhuraTokenRegExps.INTEGER_LITERAL));
		tokenDefinitions.addDefinition(new TokenDefinition(
				"FLOATING_POINT_LITERAL",
				UhuraTokenRegExps.FLOATING_POINT_LITERAL));
		tokenDefinitions.addDefinition(new TokenDefinition("BOOLEAN_LITERAL",
				UhuraTokenRegExps.BOOLEAN_LITERAL));
		tokenDefinitions.addDefinition(new TokenDefinition("IDENTIFIER",
				UhuraTokenRegExps.IDENTIFIER));
		return tokenDefinitions;
	}

	private static ProductionSet getProductions() throws GrammarException {
		ProductionSet productions = new ProductionSet();
		addStart(productions);
		addGrammarFile(productions);
		addGrammarOptionsSection(productions);
		addGrammarOption(productions);
		addHelpersSection(productions);
		addHelperDefinition(productions);
		addTokensSection(productions);
		addTokenDefinition(productions);
		addTokenConstruction(productions);
		addProductionsSection(productions);
		addProductionsDefinition(productions);
		addProductionConstruction(productions);
		addQuantifiers(productions);
		addLiterals(productions);
		return productions;
	}

	private static void addStart(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("S");
		production.addElement(new ProductionConstruction("GrammarFile"));
		productions.add(production);
	}

	private static void addGrammarFile(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("GrammarFile");
		production.addElement(new ProductionConstruction("Options"));
		production.addElement(new ProductionConstruction("Helper"));
		production.addElement(new ProductionConstruction("Tokens"));
		production.addElement(new ProductionConstruction("Productions"));
		productions.add(production);
	}

	private static void addGrammarOptionsSection(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("Options");
		production.addElement(new TextConstruction("OPTIONS"));
		production.addElement(new ProductionConstruction("GrammarOptions"));
		productions.add(production);
	}

	private static void addGrammarOption(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("GrammarOptions");
		production.addElement(new ProductionConstruction("GrammarOptions"));
		production.addElement(new ProductionConstruction("GrammarOption"));
		productions.add(production);

		production = new Production("GrammarOptions");
		productions.add(production);

		production = new Production("GrammarOption");
		production
				.addElement(new ProductionConstruction("PropertiesIdentifier"));
		production.addElement(new TokenConstruction("EQUALS"));
		production.addElement(new ProductionConstruction("Literal"));
		production.addElement(new TokenConstruction("SEMICOLON"));
		productions.add(production);

		production = new Production("PropertiesIdentifier");
		production.addElement(new TokenConstruction("IDENTIFIER"));
		productions.add(production);

		production = new Production("PropertiesIdentifier");
		production
				.addElement(new ProductionConstruction("PropertiesIdentifier"));
		production.addElement(new TextConstruction("."));
		production.addElement(new TokenConstruction("IDENTIFIER"));
		productions.add(production);

	}

	private static void addHelpersSection(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("Helper");
		production.addElement(new TextConstruction("HELPER"));
		production.addElement(new ProductionConstruction("HelperDefinitions"));
		productions.add(production);
	}

	private static void addHelperDefinition(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("HelperDefinitions");
		production.addElement(new ProductionConstruction("HelperDefinitions"));
		production.addElement(new ProductionConstruction("HelperDefinition"));
		productions.add(production);

		production = new Production("HelperDefinitions");
		productions.add(production);

		production = new Production("HelperDefinition");
		production.addElement(new TokenConstruction("IDENTIFIER"));
		production.addElement(new TokenConstruction("COLON"));
		production.addElement(new ProductionConstruction("TokenConstructions"));
		production.addElement(new TokenConstruction("SEMICOLON"));
		productions.add(production);
	}

	private static void addTokensSection(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("Tokens");
		production.addElement(new TextConstruction("TOKENS"));
		production.addElement(new ProductionConstruction("TokenDefinitions"));
		productions.add(production);
	}

	private static void addTokenDefinition(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("TokenDefinitions");
		production.addElement(new ProductionConstruction("TokenDefinitions"));
		production.addElement(new ProductionConstruction("TokenDefinition"));
		productions.add(production);

		production = new Production("TokenDefinitions");
		production.addElement(new ProductionConstruction("TokenDefinition"));
		productions.add(production);

		production = new Production("TokenDefinition");
		production.addElement(new TokenConstruction("IDENTIFIER"));
		production.addElement(new TokenConstruction("COLON"));
		production.addElement(new ProductionConstruction("TokenConstructions"));
		production.addElement(new ProductionConstruction("OptionalVisibility"));
		production.addElement(new TokenConstruction("SEMICOLON"));
		productions.add(production);

	}

	private static void addTokenConstruction(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("TokenConstructions");
		production.addElement(new ProductionConstruction("TokenConstructions"));
		production.addElement(new TextConstruction("|"));
		production.addElement(new ProductionConstruction("TokenConstruction"));
		productions.add(production);

		production = new Production("TokenConstructions");
		production.addElement(new ProductionConstruction("TokenConstruction"));
		productions.add(production);

		production = new Production("TokenConstruction");
		production.addElement(new ProductionConstruction("TokenConstruction"));
		production.addElement(new ProductionConstruction("TokenPart"));
		productions.add(production);

		production = new Production("TokenConstruction");
		production.addElement(new ProductionConstruction("TokenPart"));
		productions.add(production);

		production = new Production("TokenPart");
		production.addElement(new TextConstruction("("));
		production.addElement(new ProductionConstruction("TokenConstruction"));
		production.addElement(new TextConstruction(")"));
		production.addElement(new ProductionConstruction("OptionalQuantifier"));
		productions.add(production);

		production = new Production("TokenPart");
		production.addElement(new TokenConstruction("IDENTIFIER"));
		production.addElement(new ProductionConstruction("OptionalQuantifier"));
		productions.add(production);

		production = new Production("TokenPart");
		production.addElement(new TokenConstruction("STRING_LITERAL"));
		production.addElement(new ProductionConstruction("OptionalQuantifier"));
		productions.add(production);

		production = new Production("OptionalVisibility");
		production.addElement(new TokenConstruction("LEFT_BRACKET"));
		production.addElement(new TokenConstruction("IDENTIFIER"));
		production.addElement(new TokenConstruction("RIGHT_BRACKET"));
		productions.add(production);

		production = new Production("OptionalVisibility");
		productions.add(production);
	}

	private static void addProductionsSection(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("Productions");
		production.addElement(new TextConstruction("PRODUCTIONS"));
		production.addElement(new ProductionConstruction(
				"ProductionDefinitions"));
		productions.add(production);
	}

	private static void addProductionsDefinition(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("ProductionDefinitions");
		production.addElement(new ProductionConstruction(
				"ProductionDefinitions"));
		production
				.addElement(new ProductionConstruction("ProductionDefinition"));
		productions.add(production);

		production = new Production("ProductionDefinitions");
		productions.add(production);

		production = new Production("ProductionDefinition");
		production.addElement(new TokenConstruction("IDENTIFIER"));
		production.addElement(new TokenConstruction("COLON"));
		production.addElement(new ProductionConstruction(
				"ProductionConstructions"));
		production.addElement(new TokenConstruction("SEMICOLON"));
		productions.add(production);

	}

	private static void addProductionConstruction(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("ProductionConstructions");
		production.addElement(new ProductionConstruction(
				"ProductionConstructions"));
		production.addElement(new TextConstruction("|"));
		production.addElement(new ProductionConstruction(
				"ProductionConstruction"));
		productions.add(production);

		production = new Production("ProductionConstructions");
		production.addElement(new ProductionConstruction(
				"ProductionConstruction"));
		productions.add(production);

		production = new Production("ProductionConstruction");
		production.addElement(new ProductionConstruction(
				"AlternativeIdentifier"));
		production.addElement(new ProductionConstruction("ProductionParts"));
		productions.add(production);

		production = new Production("ProductionParts");
		production.addElement(new ProductionConstruction("ProductionParts"));
		production.addElement(new ProductionConstruction("ProductionPart"));
		productions.add(production);

		production = new Production("ProductionParts");
		production.addElement(new ProductionConstruction("ProductionPart"));
		productions.add(production);

		production = new Production("ProductionPart");
		production.addElement(new TextConstruction("("));
		production.addElement(new ProductionConstruction(
				"ProductionConstruction"));
		production.addElement(new TextConstruction(")"));
		production.addElement(new ProductionConstruction("OptionalQuantifier"));
		productions.add(production);

		production = new Production("ProductionPart");
		production.addElement(new TokenConstruction("IDENTIFIER"));
		production.addElement(new ProductionConstruction("OptionalQuantifier"));
		productions.add(production);

		production = new Production("ProductionPart");
		production.addElement(new TokenConstruction("STRING_LITERAL"));
		production.addElement(new ProductionConstruction("OptionalQuantifier"));
		productions.add(production);

		production = new Production("AlternativeIdentifier");
		production.addElement(new TokenConstruction("LEFT_CURLY_BRACKET"));
		production.addElement(new TokenConstruction("IDENTIFIER"));
		production.addElement(new TokenConstruction("RIGHT_CURLY_BRACKET"));
		productions.add(production);

		production = new Production("AlternativeIdentifier");
		productions.add(production);
	}

	private static void addQuantifiers(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("OptionalQuantifier");
		production.addElement(new TextConstruction("+"));
		productions.add(production);

		production = new Production("OptionalQuantifier");
		production.addElement(new TextConstruction("*"));
		productions.add(production);

		production = new Production("OptionalQuantifier");
		production.addElement(new TextConstruction("?"));
		productions.add(production);

		production = new Production("OptionalQuantifier");
		productions.add(production);
	}

	private static void addLiterals(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("Literal");
		production.addElement(new TokenConstruction("STRING_LITERAL"));
		productions.add(production);

		production = new Production("Literal");
		production.addElement(new TokenConstruction("INTEGER_LITERAL"));
		productions.add(production);

		production = new Production("Literal");
		production.addElement(new TokenConstruction("FLOATING_POINT_LITERAL"));
		productions.add(production);

		production = new Production("Literal");
		production.addElement(new TokenConstruction("BOOLEAN_LITERAL"));
		productions.add(production);
	}
}
