package com.puresol.uhura.grammar.uhura;

import java.util.Properties;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.NonTerminal;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.production.Terminal;
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

	public static final String UHURA_GRAMMAR_KEYWORD_OPTIONS = "OPTIONS";
	public static final String UHURA_GRAMMAR_KEYWORD_HELPER = "HELPER";
	public static final String UHURA_GRAMMAR_KEYWORD_TOKENS = "TOKENS";
	public static final String UHURA_GRAMMAR_KEYWORD_PRODUCTIONS = "PRODUCTIONS";
	public static final String UHURA_GRAMMAR_KEYWORD_HIDE = "hide";
	public static final String UHURA_GRAMMAR_KEYWORD_IGNORE = "ignore";
	public static final String UHURA_GRAMMAR_KEYWORD_NODE = "node";
	public static final String UHURA_GRAMMAR_KEYWORD_STACK = "stack";

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
		tokenDefinitions.addDefinition(new TokenDefinition("OPTIONS",
				UHURA_GRAMMAR_KEYWORD_OPTIONS));
		tokenDefinitions.addDefinition(new TokenDefinition("HELPER",
				UHURA_GRAMMAR_KEYWORD_HELPER));
		tokenDefinitions.addDefinition(new TokenDefinition("TOKENS",
				UHURA_GRAMMAR_KEYWORD_TOKENS));
		tokenDefinitions.addDefinition(new TokenDefinition("PRODUCTIONS",
				UHURA_GRAMMAR_KEYWORD_PRODUCTIONS));
		tokenDefinitions.addDefinition(new TokenDefinition("HIDE",
				UHURA_GRAMMAR_KEYWORD_HIDE));
		tokenDefinitions.addDefinition(new TokenDefinition("IGNORE",
				UHURA_GRAMMAR_KEYWORD_IGNORE));
		tokenDefinitions.addDefinition(new TokenDefinition("NODE",
				UHURA_GRAMMAR_KEYWORD_NODE));
		tokenDefinitions.addDefinition(new TokenDefinition("STACK",
				UHURA_GRAMMAR_KEYWORD_STACK));
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
		production.addConstruction(new NonTerminal("GrammarFile"));
		productions.add(production);
	}

	private static void addGrammarFile(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("GrammarFile");
		production.addConstruction(new NonTerminal("Options"));
		production.addConstruction(new NonTerminal("Helper"));
		production.addConstruction(new NonTerminal("Tokens"));
		production.addConstruction(new NonTerminal("Productions"));
		productions.add(production);
	}

	private static void addGrammarOptionsSection(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("Options");
		production.addConstruction(new Terminal("OPTIONS"));
		production.addConstruction(new NonTerminal("GrammarOptions"));
		productions.add(production);
	}

	private static void addGrammarOption(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("GrammarOptions");
		production.addConstruction(new NonTerminal("GrammarOptions"));
		production.addConstruction(new NonTerminal("GrammarOption"));
		production.setNode(true);
		production.setStackingAllowed(false);
		productions.add(production);

		production = new Production("GrammarOptions");
		production.setNode(true);
		production.setStackingAllowed(false);
		productions.add(production);

		production = new Production("GrammarOption");
		production.addConstruction(new NonTerminal("PropertiesIdentifier"));
		production.addConstruction(new Terminal("EQUALS"));
		production.addConstruction(new NonTerminal("Literal"));
		production.addConstruction(new Terminal("SEMICOLON"));
		productions.add(production);

		production = new Production("PropertiesIdentifier");
		production.addConstruction(new Terminal("IDENTIFIER"));
		productions.add(production);

		production = new Production("PropertiesIdentifier");
		production.addConstruction(new NonTerminal("PropertiesIdentifier"));
		production.addConstruction(new Terminal("DOT"));
		production.addConstruction(new Terminal("IDENTIFIER"));
		productions.add(production);
	}

	private static void addHelpersSection(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("Helper");
		production.addConstruction(new Terminal("HELPER"));
		production.addConstruction(new NonTerminal("HelperDefinitions"));
		productions.add(production);
	}

	private static void addHelperDefinition(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("HelperDefinitions");
		production.addConstruction(new NonTerminal("HelperDefinitions"));
		production.addConstruction(new NonTerminal("HelperDefinition"));
		production.setNode(true);
		production.setStackingAllowed(false);
		productions.add(production);

		production = new Production("HelperDefinitions");
		production.setNode(true);
		production.setStackingAllowed(false);
		productions.add(production);

		production = new Production("HelperDefinition");
		production.addConstruction(new Terminal("IDENTIFIER"));
		production.addConstruction(new Terminal("COLON"));
		production.addConstruction(new NonTerminal("TokenConstructions"));
		production.addConstruction(new Terminal("SEMICOLON"));
		productions.add(production);
	}

	private static void addTokensSection(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("Tokens");
		production.addConstruction(new Terminal("TOKENS"));
		production.addConstruction(new NonTerminal("TokenDefinitions"));
		productions.add(production);
	}

	private static void addTokenDefinition(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("TokenDefinitions");
		production.addConstruction(new NonTerminal("TokenDefinitions"));
		production.addConstruction(new NonTerminal("TokenDefinition"));
		production.setNode(true);
		production.setStackingAllowed(false);
		productions.add(production);

		production = new Production("TokenDefinitions");
		production.addConstruction(new NonTerminal("TokenDefinition"));
		production.setNode(true);
		production.setStackingAllowed(false);
		productions.add(production);

		production = new Production("TokenDefinition");
		production.addConstruction(new Terminal("IDENTIFIER"));
		production.addConstruction(new Terminal("COLON"));
		production.addConstruction(new NonTerminal("TokenConstructions"));
		production.addConstruction(new NonTerminal("OptionalVisibility"));
		production.addConstruction(new Terminal("SEMICOLON"));
		productions.add(production);

	}

	private static void addTokenConstruction(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("TokenConstructions");
		production.addConstruction(new NonTerminal("TokenConstructions"));
		production.addConstruction(new Terminal("VERTICAL_BAR"));
		production.addConstruction(new NonTerminal("TokenConstruction"));
		production.setNode(true);
		production.setStackingAllowed(false);
		productions.add(production);

		production = new Production("TokenConstructions");
		production.addConstruction(new NonTerminal("TokenConstruction"));
		production.setNode(true);
		production.setStackingAllowed(false);
		productions.add(production);

		production = new Production("TokenConstruction");
		production.addConstruction(new NonTerminal("TokenConstruction"));
		production.addConstruction(new NonTerminal("TokenPart"));
		productions.add(production);

		production = new Production("TokenConstruction");
		production.addConstruction(new NonTerminal("TokenPart"));
		productions.add(production);

		production = new Production("TokenPart");
		production.addConstruction(new Terminal("LPAREN"));
		production.addConstruction(new NonTerminal("TokenConstruction"));
		production.addConstruction(new Terminal("RPAREN"));
		production.addConstruction(new NonTerminal("OptionalQuantifier"));
		productions.add(production);

		production = new Production("TokenPart");
		production.addConstruction(new Terminal("IDENTIFIER"));
		production.addConstruction(new NonTerminal("OptionalQuantifier"));
		productions.add(production);

		production = new Production("TokenPart");
		production.addConstruction(new Terminal("STRING_LITERAL"));
		production.addConstruction(new NonTerminal("OptionalQuantifier"));
		productions.add(production);

		production = new Production("OptionalVisibility");
		production.addConstruction(new Terminal("LEFT_BRACKET"));
		production.addConstruction(new Terminal("HIDE"));
		production.addConstruction(new Terminal("RIGHT_BRACKET"));
		productions.add(production);

		production = new Production("OptionalVisibility");
		production.addConstruction(new Terminal("LEFT_BRACKET"));
		production.addConstruction(new Terminal("IGNORE"));
		production.addConstruction(new Terminal("RIGHT_BRACKET"));
		productions.add(production);

		production = new Production("OptionalVisibility");
		productions.add(production);
	}

	private static void addProductionsSection(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("Productions");
		production.addConstruction(new Terminal("PRODUCTIONS"));
		production.addConstruction(new NonTerminal("ProductionDefinitions"));
		productions.add(production);
	}

	private static void addProductionsDefinition(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("ProductionDefinitions");
		production.addConstruction(new NonTerminal("ProductionDefinitions"));
		production.addConstruction(new NonTerminal("ProductionDefinition"));
		production.setNode(true);
		production.setStackingAllowed(false);
		productions.add(production);

		production = new Production("ProductionDefinitions");
		production.setNode(true);
		production.setStackingAllowed(false);
		productions.add(production);

		production = new Production("ProductionDefinition");
		production.addConstruction(new Terminal("IDENTIFIER"));
		production.addConstruction(new Terminal("COLON"));
		production.addConstruction(new NonTerminal("ProductionConstructions"));
		production.addConstruction(new Terminal("SEMICOLON"));
		productions.add(production);

	}

	private static void addProductionConstruction(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("ProductionConstructions");
		production.addConstruction(new NonTerminal("ProductionConstructions"));
		production.addConstruction(new Terminal("VERTICAL_BAR"));
		production.addConstruction(new NonTerminal("ProductionConstruction"));
		production.setNode(true);
		production.setStackingAllowed(false);
		productions.add(production);

		production = new Production("ProductionConstructions");
		production.addConstruction(new NonTerminal("ProductionConstruction"));
		production.setNode(true);
		production.setStackingAllowed(false);
		productions.add(production);

		production = new Production("ProductionConstruction");
		production.addConstruction(new NonTerminal("AlternativeIdentifier"));
		production.addConstruction(new NonTerminal("ProductionParts"));
		production.addConstruction(new NonTerminal("OptionalOptions"));
		productions.add(production);

		production = new Production("ProductionParts");
		production.addConstruction(new NonTerminal("ProductionParts"));
		production.addConstruction(new NonTerminal("ProductionPart"));
		production.setNode(true);
		production.setStackingAllowed(false);
		productions.add(production);

		// empty productions are also allowed...
		production = new Production("ProductionConstruction");
		productions.add(production);

		production = new Production("ProductionParts");
		production.addConstruction(new NonTerminal("ProductionPart"));
		production.setNode(true);
		production.setStackingAllowed(false);
		productions.add(production);

		production = new Production("ProductionPart");
		production.addConstruction(new Terminal("LPAREN"));
		production.addConstruction(new NonTerminal("ProductionConstructions"));
		production.addConstruction(new Terminal("RPAREN"));
		production.addConstruction(new NonTerminal("OptionalQuantifier"));
		productions.add(production);

		production = new Production("ProductionPart");
		production.addConstruction(new Terminal("IDENTIFIER"));
		production.addConstruction(new NonTerminal("OptionalQuantifier"));
		productions.add(production);

		production = new Production("ProductionPart");
		production.addConstruction(new Terminal("STRING_LITERAL"));
		production.addConstruction(new NonTerminal("OptionalQuantifier"));
		productions.add(production);

		production = new Production("AlternativeIdentifier");
		production.addConstruction(new Terminal("LEFT_CURLY_BRACKET"));
		production.addConstruction(new Terminal("IDENTIFIER"));
		production.addConstruction(new Terminal("RIGHT_CURLY_BRACKET"));
		productions.add(production);

		production = new Production("AlternativeIdentifier");
		productions.add(production);

		production = new Production("OptionalOptions");
		production.addConstruction(new Terminal("LEFT_BRACKET"));
		production.addConstruction(new NonTerminal("OptionList"));
		production.addConstruction(new Terminal("RIGHT_BRACKET"));
		productions.add(production);

		production = new Production("OptionalOptions");
		productions.add(production);

		production = new Production("OptionList");
		production.addConstruction(new NonTerminal("OptionList"));
		production.addConstruction(new Terminal("COMMA"));
		production.addConstruction(new NonTerminal("Option"));
		production.setNode(true);
		production.setStackingAllowed(false);
		productions.add(production);

		production = new Production("OptionList");
		production.addConstruction(new NonTerminal("Option"));
		production.setNode(true);
		production.setStackingAllowed(false);
		productions.add(production);

		production = new Production("Option");
		production.addConstruction(new Terminal("NODE"));
		production.addConstruction(new Terminal("EQUALS"));
		production.addConstruction(new Terminal("BOOLEAN_LITERAL"));
		productions.add(production);

		production = new Production("Option");
		production.addConstruction(new Terminal("STACK"));
		production.addConstruction(new Terminal("EQUALS"));
		production.addConstruction(new Terminal("BOOLEAN_LITERAL"));
		productions.add(production);
	}

	private static void addQuantifiers(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("OptionalQuantifier");
		production.addConstruction(new Terminal("PLUS"));
		productions.add(production);

		production = new Production("OptionalQuantifier");
		production.addConstruction(new Terminal("STAR"));
		productions.add(production);

		production = new Production("OptionalQuantifier");
		production.addConstruction(new Terminal("QUESTION_MARK"));
		productions.add(production);

		production = new Production("OptionalQuantifier");
		productions.add(production);
	}

	private static void addLiterals(ProductionSet productions)
			throws GrammarException {
		Production production = new Production("Literal");
		production.addConstruction(new Terminal("STRING_LITERAL"));
		productions.add(production);

		production = new Production("Literal");
		production.addConstruction(new Terminal("INTEGER_LITERAL"));
		productions.add(production);

		production = new Production("Literal");
		production.addConstruction(new Terminal("FLOATING_POINT_LITERAL"));
		productions.add(production);

		production = new Production("Literal");
		production.addConstruction(new Terminal("BOOLEAN_LITERAL"));
		productions.add(production);
	}
}
