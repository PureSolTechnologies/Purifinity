package com.puresol.uhura.grammar.uhura;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionProductionElement;
import com.puresol.uhura.grammar.production.Quantity;
import com.puresol.uhura.grammar.production.TextProductionElement;
import com.puresol.uhura.grammar.production.TokenProductionElement;
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

		tokenDefinitions.addRule(new TokenDefinition("WHITE_SPACE",
				UhuraTokenRegExps.WHITE_SPACE, Visibility.HIDDEN));
		tokenDefinitions.addRule(new TokenDefinition("LINE_COMMENT",
				UhuraTokenRegExps.END_OF_LINE_COMMENT, Visibility.HIDDEN));

		tokenDefinitions.addRule(new TokenDefinition("EQUALS", "="));
		tokenDefinitions.addRule(new TokenDefinition("SEMICOLON", ";"));
		tokenDefinitions.addRule(new TokenDefinition("COLON", ":"));
		tokenDefinitions.addRule(new TokenDefinition("DOT", "."));

		tokenDefinitions.addRule(new TokenDefinition("OPTIONS", "OPTIONS"));
		tokenDefinitions.addRule(new TokenDefinition("HELPER", "HELPER"));
		tokenDefinitions.addRule(new TokenDefinition("TOKENS", "TOKENS"));
		tokenDefinitions.addRule(new TokenDefinition("PRODUCTIONS",
				"PRODUCTIONS"));

		tokenDefinitions.addRule(new TokenDefinition("STRING_LITERAL",
				UhuraTokenRegExps.STRING_LITERAL));
		tokenDefinitions.addRule(new TokenDefinition("IDENTIFIER",
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
		grammar.addElement(new ProductionProductionElement("GrammarOptions"));
		grammar.addElement(new ProductionProductionElement("Helper"));
		grammar.addElement(new ProductionProductionElement("Tokens"));
		grammar.addElement(new ProductionProductionElement("Productions"));
		getProductions().addRule(grammar);
	}

	private void addGrammarOptionsSection() throws GrammarException {
		Production grammarOptions = new Production("GrammarOptions");
		grammarOptions.addElement(new TextProductionElement("OPTIONS"));
		grammarOptions.addElement(new ProductionProductionElement(
				"GrammarOption", Quantity.EXPECT_MANY));
		getProductions().addRule(grammarOptions);
	}

	private void addGrammarOption() throws GrammarException {
		Production grammarOptions = new Production("GrammarOption");
		grammarOptions.addElement(new TokenProductionElement("IDENTIFIER"));
		grammarOptions.addElement(new TokenProductionElement("EQUALS"));
		grammarOptions.addElement(new TokenProductionElement("STRING_LITERAL"));
		grammarOptions.addElement(new TokenProductionElement("SEMICOLON"));
		getProductions().addRule(grammarOptions);
	}

	private void addHelpersSection() throws GrammarException {
		Production helper = new Production("Helper");
		helper.addElement(new TextProductionElement("HELPER"));
		getProductions().addRule(helper);
	}

	private void addTokensSection() throws GrammarException {
		Production tokens = new Production("Tokens");
		tokens.addElement(new TextProductionElement("TOKENS"));
		getProductions().addRule(tokens);
	}

	private void addProductionsSection() throws GrammarException {
		Production productions = new Production("Productions");
		productions.addElement(new TextProductionElement("PRODUCTIONS"));
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
