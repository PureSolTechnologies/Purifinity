package com.puresol.uhura.grammar.uhura;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.NameReferenceTable;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionProductionElement;
import com.puresol.uhura.grammar.production.TextProductionElement;
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
		NameReferenceTable nameReference = getNameReference();
		TokenDefinitionSet tokenDefinitions = getTokenDefinitions();

		tokenDefinitions.addRule(new TokenDefinition(nameReference
				.defineTokenAndGetId("WHITE_SPACE"),
				UhuraTokenRegExps.WHITE_SPACE, Visibility.HIDDEN));
		tokenDefinitions.addRule(new TokenDefinition(nameReference
				.defineTokenAndGetId("LINE_COMMENT"),
				UhuraTokenRegExps.END_OF_LINE_COMMENT, Visibility.HIDDEN));

		tokenDefinitions.addRule(new TokenDefinition(nameReference
				.defineTokenAndGetId("EQUALS"), "="));
		tokenDefinitions.addRule(new TokenDefinition(nameReference
				.defineTokenAndGetId("SEMICOLON"), ";"));
		tokenDefinitions.addRule(new TokenDefinition(nameReference
				.defineTokenAndGetId("COLON"), ":"));
		tokenDefinitions.addRule(new TokenDefinition(nameReference
				.defineTokenAndGetId("DOT"), "."));

		tokenDefinitions.addRule(new TokenDefinition(nameReference
				.defineTokenAndGetId("OPTIONS"), "OPTIONS"));
		tokenDefinitions.addRule(new TokenDefinition(nameReference
				.defineTokenAndGetId("HELPER"), "HELPER"));
		tokenDefinitions.addRule(new TokenDefinition(nameReference
				.defineTokenAndGetId("TOKENS"), "TOKENS"));
		tokenDefinitions.addRule(new TokenDefinition(nameReference
				.defineTokenAndGetId("PRODUCTIONS"), "PRODUCTIONS"));

		tokenDefinitions.addRule(new TokenDefinition(nameReference
				.defineTokenAndGetId("STRING_LITERAL"),
				UhuraTokenRegExps.STRING_LITERAL));
		tokenDefinitions.addRule(new TokenDefinition(nameReference
				.defineTokenAndGetId("IDENTIFIER"),
				UhuraTokenRegExps.IDENTIFIER));
	}

	private void addProductions() throws GrammarException {
		addGrammarFile();
		addOptionsSection();
		addHelpersSection();
		addTokensSection();
		addProductionsSection();
	}

	private void addGrammarFile() throws GrammarException {
		Production grammar = new Production(getNameReference()
				.defineProductionAndGetId("grammar_file"));
		grammar.addElement(new ProductionProductionElement(getNameReference()
				.getProduction("grammar_options")));
		grammar.addElement(new ProductionProductionElement(getNameReference()
				.getProduction("helper")));
		grammar.addElement(new ProductionProductionElement(getNameReference()
				.getProduction("tokens")));
		grammar.addElement(new ProductionProductionElement(getNameReference()
				.getProduction("productions")));
		getProductions().addRule(grammar);
	}

	private void addOptionsSection() throws GrammarException {
		Production grammarOptions = new Production(getNameReference()
				.defineProductionAndGetId("grammar_options"));
		grammarOptions.addElement(new TextProductionElement("OPTIONS"));
		getProductions().addRule(grammarOptions);
	}

	private void addHelpersSection() throws GrammarException {
		Production helper = new Production(getNameReference()
				.defineProductionAndGetId("helper"));
		helper.addElement(new TextProductionElement("HELPER"));
		getProductions().addRule(helper);
	}

	private void addTokensSection() throws GrammarException {
		Production tokens = new Production(getNameReference()
				.defineProductionAndGetId("tokens"));
		tokens.addElement(new TextProductionElement("TOKENS"));
		getProductions().addRule(tokens);
	}

	private void addProductionsSection() throws GrammarException {
		Production productions = new Production(getNameReference()
				.defineProductionAndGetId("productions"));
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
