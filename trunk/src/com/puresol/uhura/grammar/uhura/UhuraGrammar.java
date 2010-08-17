package com.puresol.uhura.grammar.uhura;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionConstruction;
import com.puresol.uhura.grammar.production.Quantity;
import com.puresol.uhura.grammar.production.TextConstruction;
import com.puresol.uhura.grammar.production.TokenConstruction;
import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;
import com.puresol.uhura.grammar.token.Visibility;

public class UhuraGrammar extends Grammar {

	public UhuraGrammar() throws GrammarException {
		super();
		addTokenDefinitions();
		addProductions();
	}

	private void addTokenDefinitions() throws GrammarException {
		TokenDefinitionSet tokenDefinitions = getTokenDefinitions();

		tokenDefinitions.addDefinition(new TokenDefinition("WHITE_SPACE",
				UhuraTokenRegExps.WHITE_SPACE, Visibility.HIDDEN));
		tokenDefinitions.addDefinition(new TokenDefinition("LINE_COMMENT",
				UhuraTokenRegExps.END_OF_LINE_COMMENT, Visibility.HIDDEN));

		tokenDefinitions.addDefinition(new TokenDefinition("EQUALS", "="));
		tokenDefinitions.addDefinition(new TokenDefinition("SEMICOLON", ";"));
		tokenDefinitions.addDefinition(new TokenDefinition("COLON", ":"));
		tokenDefinitions.addDefinition(new TokenDefinition("DOT", "."));

		tokenDefinitions.addDefinition(new TokenDefinition("OPTIONS", "OPTIONS"));
		tokenDefinitions.addDefinition(new TokenDefinition("HELPER", "HELPER"));
		tokenDefinitions.addDefinition(new TokenDefinition("TOKENS", "TOKENS"));
		tokenDefinitions.addDefinition(new TokenDefinition("PRODUCTIONS",
				"PRODUCTIONS"));

		tokenDefinitions.addDefinition(new TokenDefinition("STRING_LITERAL",
				UhuraTokenRegExps.STRING_LITERAL));
		tokenDefinitions.addDefinition(new TokenDefinition("IDENTIFIER",
				UhuraTokenRegExps.IDENTIFIER));
	}

	private void addProductions() throws GrammarException {
		addGrammarFile();
		addGrammarOptionsSection();
		addGrammarOption();
		addHelpersSection();
		addTokensSection();
		addProductionsSection();
	}

	private void addGrammarFile() throws GrammarException {
		Production grammar = new Production("GrammarFile");
		grammar.addElement(new ProductionConstruction("GrammarOptions"));
		grammar.addElement(new ProductionConstruction("Helper"));
		grammar.addElement(new ProductionConstruction("Tokens"));
		grammar.addElement(new ProductionConstruction("Productions"));
		getProductions().addRule(grammar);
	}

	private void addGrammarOptionsSection() throws GrammarException {
		Production grammarOptions = new Production("GrammarOptions");
		grammarOptions.addElement(new TextConstruction("OPTIONS"));
		grammarOptions.addElement(new ProductionConstruction(
				"GrammarOption", Quantity.EXPECT_MANY));
		getProductions().addRule(grammarOptions);
	}

	private void addGrammarOption() throws GrammarException {
		Production grammarOptions = new Production("GrammarOption");
		grammarOptions.addElement(new TokenConstruction("IDENTIFIER"));
		grammarOptions.addElement(new TokenConstruction("EQUALS"));
		grammarOptions.addElement(new TokenConstruction("STRING_LITERAL"));
		grammarOptions.addElement(new TokenConstruction("SEMICOLON"));
		getProductions().addRule(grammarOptions);
	}

	private void addHelpersSection() throws GrammarException {
		Production helper = new Production("Helper");
		helper.addElement(new TextConstruction("HELPER"));
		getProductions().addRule(helper);
	}

	private void addTokensSection() throws GrammarException {
		Production tokens = new Production("Tokens");
		tokens.addElement(new TextConstruction("TOKENS"));
		getProductions().addRule(tokens);
	}

	private void addProductionsSection() throws GrammarException {
		Production productions = new Production("Productions");
		productions.addElement(new TextConstruction("PRODUCTIONS"));
		getProductions().addRule(productions);
	}

	// private void addTokenDefinition() {
	// Production tokenDefinition = new Production("TOKEN_DEFINITION");
	// tokenDefinition.addElement(new TextProductionElement("IDENTIFIER",
	// ProductionElementType.TOKEN));
	// tokenDefinition.addElement(new TextProductionElement("EQUALS",
	// ProductionElementType.TOKEN));
	// tokenDefinition.addElement(new TextProductionElement("STRING_LITERAL",
	// ProductionElementType.TOKEN));
	// tokenDefinition.addElement(new TextProductionElement("options",
	// ProductionElementType.PART, Quantity.ACCEPT));
	// tokenDefinition.addElement(new TextProductionElement("SEMICOLON",
	// ProductionElementType.TOKEN));
	// addRule(tokenDefinition);
	// }
	//
	// private void addOptions() {
	// Production options = new Production("options");
	// options.addElement(new TextProductionElement("COLON",
	// ProductionElementType.TOKEN));
	// options.addElement(new TextProductionElement("IDENTIFIER",
	// ProductionElementType.TOKEN));
	// addRule(options);
	// }

}
