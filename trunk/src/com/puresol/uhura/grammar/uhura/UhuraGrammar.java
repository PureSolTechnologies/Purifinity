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
		addCommentsAndAllSymbols(tokenDefinitions);
		addKeywords(tokenDefinitions);
		addLiteralsAndIdentifier(tokenDefinitions);
		return tokenDefinitions;
	}

	private static void addCommentsAndAllSymbols(
			TokenDefinitionSet tokenDefinitions) throws GrammarException {
		tokenDefinitions.addDefinition(new TokenDefinition("COMMENT",
				UhuraTokenRegExps.COMMENT, Visibility.HIDDEN));
		tokenDefinitions.addDefinition(new TokenDefinition("WHITE_SPACE",
				UhuraTokenRegExps.WHITE_SPACE, Visibility.HIDDEN));

		tokenDefinitions.addDefinition(new TokenDefinition("EQUALS", "="));
		tokenDefinitions.addDefinition(new TokenDefinition("SEMICOLON", ";"));
		tokenDefinitions.addDefinition(new TokenDefinition("COMMA", ","));
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
	}

	private static void addKeywords(TokenDefinitionSet tokenDefinitions)
			throws GrammarException {
		tokenDefinitions
				.addDefinition(new TokenDefinition("OPTIONS", "OPTIONS"));
		tokenDefinitions.addDefinition(new TokenDefinition("HELPER", "HELPER"));
		tokenDefinitions.addDefinition(new TokenDefinition("TOKENS", "TOKENS"));
		tokenDefinitions.addDefinition(new TokenDefinition("PRODUCTIONS",
				"PRODUCTIONS"));
		tokenDefinitions.addDefinition(new TokenDefinition("HIDDEN", "hidden"));
		tokenDefinitions.addDefinition(new TokenDefinition("NODE", "node"));
	}

	private static void addLiteralsAndIdentifier(
			TokenDefinitionSet tokenDefinitions) throws GrammarException {
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
		Production production = new Production("_START_");
		production.addConstruction(new ProductionConstruction("GrammarFile"));
		productions.add(production);
	}

	private static void addGrammarFile(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("GrammarFile");
		production.addConstruction(new ProductionConstruction("Options"));
		production.addConstruction(new ProductionConstruction("Helper"));
		production.addConstruction(new ProductionConstruction("Tokens"));
		production.addConstruction(new ProductionConstruction("Productions"));
		productions.add(production);
	}

	private static void addGrammarOptionsSection(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("Options");
		production.addConstruction(new TextConstruction("OPTIONS"));
		production
				.addConstruction(new ProductionConstruction("GrammarOptions"));
		productions.add(production);
	}

	private static void addGrammarOption(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("GrammarOptions");
		production
				.addConstruction(new ProductionConstruction("GrammarOptions"));
		production.addConstruction(new ProductionConstruction("GrammarOption"));
		production.setNode(false);
		productions.add(production);

		production = new Production("GrammarOptions");
		production.setNode(false);
		productions.add(production);

		production = new Production("GrammarOption");
		production.addConstruction(new ProductionConstruction(
				"PropertiesIdentifier"));
		production.addConstruction(new TextConstruction("="));
		production.addConstruction(new ProductionConstruction("Literal"));
		production.addConstruction(new TextConstruction(";"));
		productions.add(production);

		production = new Production("PropertiesIdentifier");
		production.addConstruction(new TokenConstruction("IDENTIFIER"));
		productions.add(production);

		production = new Production("PropertiesIdentifier");
		production.addConstruction(new ProductionConstruction(
				"PropertiesIdentifier"));
		production.addConstruction(new TextConstruction("."));
		production.addConstruction(new TokenConstruction("IDENTIFIER"));
		productions.add(production);
	}

	private static void addHelpersSection(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("Helper");
		production.addConstruction(new TextConstruction("HELPER"));
		production.addConstruction(new ProductionConstruction(
				"HelperDefinitions"));
		productions.add(production);
	}

	private static void addHelperDefinition(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("HelperDefinitions");
		production.addConstruction(new ProductionConstruction(
				"HelperDefinitions"));
		production.addConstruction(new ProductionConstruction(
				"HelperDefinition"));
		productions.add(production);

		production = new Production("HelperDefinitions");
		productions.add(production);

		production = new Production("HelperDefinition");
		production.addConstruction(new TokenConstruction("IDENTIFIER"));
		production.addConstruction(new TokenConstruction("COLON"));
		production.addConstruction(new ProductionConstruction(
				"TokenConstructions"));
		production.addConstruction(new TextConstruction(";"));
		productions.add(production);
	}

	private static void addTokensSection(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("Tokens");
		production.addConstruction(new TextConstruction("TOKENS"));
		production.addConstruction(new ProductionConstruction(
				"TokenDefinitions"));
		productions.add(production);
	}

	private static void addTokenDefinition(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("TokenDefinitions");
		production.addConstruction(new ProductionConstruction(
				"TokenDefinitions"));
		production
				.addConstruction(new ProductionConstruction("TokenDefinition"));
		production.setNode(false);
		productions.add(production);

		production = new Production("TokenDefinitions");
		production
				.addConstruction(new ProductionConstruction("TokenDefinition"));
		production.setNode(false);
		productions.add(production);

		production = new Production("TokenDefinition");
		production.addConstruction(new TokenConstruction("IDENTIFIER"));
		production.addConstruction(new TokenConstruction("COLON"));
		production.addConstruction(new ProductionConstruction(
				"TokenConstructions"));
		production.addConstruction(new ProductionConstruction(
				"OptionalVisibility"));
		production.addConstruction(new TextConstruction(";"));
		productions.add(production);

	}

	private static void addTokenConstruction(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("TokenConstructions");
		production.addConstruction(new ProductionConstruction(
				"TokenConstructions"));
		production.addConstruction(new TextConstruction("|"));
		production.addConstruction(new ProductionConstruction(
				"TokenConstruction"));
		productions.add(production);

		production = new Production("TokenConstructions");
		production.addConstruction(new ProductionConstruction(
				"TokenConstruction"));
		productions.add(production);

		production = new Production("TokenConstruction");
		production.addConstruction(new ProductionConstruction(
				"TokenConstruction"));
		production.addConstruction(new ProductionConstruction("TokenPart"));
		productions.add(production);

		production = new Production("TokenConstruction");
		production.addConstruction(new ProductionConstruction("TokenPart"));
		productions.add(production);

		production = new Production("TokenPart");
		production.addConstruction(new TextConstruction("("));
		production.addConstruction(new ProductionConstruction(
				"TokenConstruction"));
		production.addConstruction(new TextConstruction(")"));
		production.addConstruction(new ProductionConstruction(
				"OptionalQuantifier"));
		productions.add(production);

		production = new Production("TokenPart");
		production.addConstruction(new TokenConstruction("IDENTIFIER"));
		production.addConstruction(new ProductionConstruction(
				"OptionalQuantifier"));
		productions.add(production);

		production = new Production("TokenPart");
		production.addConstruction(new TokenConstruction("STRING_LITERAL"));
		production.addConstruction(new ProductionConstruction(
				"OptionalQuantifier"));
		productions.add(production);

		production = new Production("OptionalVisibility");
		production.addConstruction(new TokenConstruction("LEFT_BRACKET"));
		production.addConstruction(new TextConstruction("hidden"));
		production.addConstruction(new TokenConstruction("RIGHT_BRACKET"));
		productions.add(production);

		production = new Production("OptionalVisibility");
		productions.add(production);
	}

	private static void addProductionsSection(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("Productions");
		production.addConstruction(new TextConstruction("PRODUCTIONS"));
		production.addConstruction(new ProductionConstruction(
				"ProductionDefinitions"));
		productions.add(production);
	}

	private static void addProductionsDefinition(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("ProductionDefinitions");
		production.addConstruction(new ProductionConstruction(
				"ProductionDefinitions"));
		production.addConstruction(new ProductionConstruction(
				"ProductionDefinition"));
		production.setNode(false);
		productions.add(production);

		production = new Production("ProductionDefinitions");
		production.setNode(false);
		productions.add(production);

		production = new Production("ProductionDefinition");
		production.addConstruction(new TokenConstruction("IDENTIFIER"));
		production.addConstruction(new TokenConstruction("COLON"));
		production.addConstruction(new ProductionConstruction(
				"ProductionConstructions"));
		production.addConstruction(new TextConstruction(";"));
		productions.add(production);

	}

	private static void addProductionConstruction(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("ProductionConstructions");
		production.addConstruction(new ProductionConstruction(
				"ProductionConstructions"));
		production.addConstruction(new TextConstruction("|"));
		production.addConstruction(new ProductionConstruction(
				"ProductionConstruction"));
		production.setNode(false);
		productions.add(production);

		production = new Production("ProductionConstructions");
		production.addConstruction(new ProductionConstruction(
				"ProductionConstruction"));
		production.setNode(false);
		productions.add(production);

		production = new Production("ProductionConstruction");
		production.addConstruction(new ProductionConstruction(
				"AlternativeIdentifier"));
		production
				.addConstruction(new ProductionConstruction("ProductionParts"));
		production
				.addConstruction(new ProductionConstruction("OptionalOptions"));
		productions.add(production);

		production = new Production("ProductionParts");
		production
				.addConstruction(new ProductionConstruction("ProductionParts"));
		production
				.addConstruction(new ProductionConstruction("ProductionPart"));
		production.setNode(false);
		productions.add(production);

		production = new Production("ProductionParts");
		production
				.addConstruction(new ProductionConstruction("ProductionPart"));
		production.setNode(false);
		productions.add(production);

		production = new Production("ProductionPart");
		production.addConstruction(new TextConstruction("("));
		production.addConstruction(new ProductionConstruction(
				"ProductionConstruction"));
		production.addConstruction(new TextConstruction(")"));
		production.addConstruction(new ProductionConstruction(
				"OptionalQuantifier"));
		productions.add(production);

		production = new Production("ProductionPart");
		production.addConstruction(new TokenConstruction("IDENTIFIER"));
		production.addConstruction(new ProductionConstruction(
				"OptionalQuantifier"));
		productions.add(production);

		production = new Production("ProductionPart");
		production.addConstruction(new TokenConstruction("STRING_LITERAL"));
		production.addConstruction(new ProductionConstruction(
				"OptionalQuantifier"));
		productions.add(production);

		production = new Production("AlternativeIdentifier");
		production.addConstruction(new TokenConstruction("LEFT_CURLY_BRACKET"));
		production.addConstruction(new TokenConstruction("IDENTIFIER"));
		production
				.addConstruction(new TokenConstruction("RIGHT_CURLY_BRACKET"));
		productions.add(production);

		production = new Production("AlternativeIdentifier");
		productions.add(production);

		production = new Production("OptionalOptions");
		production.addConstruction(new TokenConstruction("LEFT_BRACKET"));
		production.addConstruction(new ProductionConstruction(
				"OptionalOptionList"));
		production.addConstruction(new TokenConstruction("RIGHT_BRACKET"));
		productions.add(production);

		production = new Production("OptionalOptions");
		productions.add(production);

		production = new Production("OptionalOptionList");
		production.addConstruction(new ProductionConstruction(
				"OptionalOptionList"));
		production.addConstruction(new TokenConstruction("COMMA"));
		production
				.addConstruction(new ProductionConstruction("OptionalOption"));
		production.setNode(false);
		productions.add(production);

		production = new Production("OptionalOptionList");
		production
				.addConstruction(new ProductionConstruction("OptionalOption"));
		production.setNode(false);
		productions.add(production);

		production = new Production("OptionalOption");
		production.addConstruction(new TextConstruction("node"));
		production.addConstruction(new TextConstruction("="));
		production.addConstruction(new TokenConstruction("BOOLEAN_LITERAL"));
		productions.add(production);
	}

	private static void addQuantifiers(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("OptionalQuantifier");
		production.addConstruction(new TextConstruction("+"));
		productions.add(production);

		production = new Production("OptionalQuantifier");
		production.addConstruction(new TextConstruction("*"));
		productions.add(production);

		production = new Production("OptionalQuantifier");
		production.addConstruction(new TextConstruction("?"));
		productions.add(production);

		production = new Production("OptionalQuantifier");
		productions.add(production);
	}

	private static void addLiterals(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("Literal");
		production.addConstruction(new TokenConstruction("STRING_LITERAL"));
		productions.add(production);

		production = new Production("Literal");
		production.addConstruction(new TokenConstruction("INTEGER_LITERAL"));
		productions.add(production);

		production = new Production("Literal");
		production.addConstruction(new TokenConstruction(
				"FLOATING_POINT_LITERAL"));
		productions.add(production);

		production = new Production("Literal");
		production.addConstruction(new TokenConstruction("BOOLEAN_LITERAL"));
		productions.add(production);
	}
}
