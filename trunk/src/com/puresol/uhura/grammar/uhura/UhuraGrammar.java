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

		tokenDefinitions
				.addDefinition(new TokenDefinition("OPTIONS", "OPTIONS"));
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
		addStart();
		addGrammarFile();
		addGrammarOptionsSection();
		addGrammarOption();
		addHelpersSection();
		addHelperDefinition();
		addTokensSection();
		addTokenDefinition();
		addProductionsSection();
		addTokenConstruction();
	}

	private void addStart() throws GrammarException {
		Production production = new Production("S");
		production.addElement(new ProductionConstruction("GrammarFile"));
		getProductions().addRule(production);
	}

	private void addGrammarFile() throws GrammarException {
		Production production = new Production("GrammarFile");
		production.addElement(new ProductionConstruction("GrammarOptions"));
		production.addElement(new ProductionConstruction("Helper"));
		production.addElement(new ProductionConstruction("Tokens"));
		production.addElement(new ProductionConstruction("Productions"));
		getProductions().addRule(production);
	}

	private void addGrammarOptionsSection() throws GrammarException {
		Production production = new Production("GrammarOptions");
		production.addElement(new TextConstruction("OPTIONS"));
		production.addElement(new ProductionConstruction("GrammarOption",
				Quantity.EXPECT_MANY));
		getProductions().addRule(production);
	}

	private void addGrammarOption() throws GrammarException {
		Production production = new Production("GrammarOption");
		production.addElement(new TokenConstruction("IDENTIFIER"));
		production.addElement(new TokenConstruction("EQUALS"));
		production.addElement(new TokenConstruction("STRING_LITERAL"));
		production.addElement(new TokenConstruction("SEMICOLON"));
		getProductions().addRule(production);
	}

	private void addHelpersSection() throws GrammarException {
		Production production = new Production("Helper");
		production.addElement(new TextConstruction("HELPER"));
		production.addElement(new TokenConstruction("HelperDefinition",
				Quantity.ACCEPT_MANY));
		getProductions().addRule(production);
	}

	private void addHelperDefinition() throws GrammarException {
		Production production = new Production("HelperDefinition");
		production.addElement(new TokenConstruction("IDENTIFIER"));
		production.addElement(new TokenConstruction("EQUALS"));
		production.addElement(new TokenConstruction("TokenConstruction",
				Quantity.EXPECT_MANY));
		production.addElement(new TokenConstruction("SEMICOLON"));
		getProductions().addRule(production);
	}

	private void addTokensSection() throws GrammarException {
		Production production = new Production("Tokens");
		production.addElement(new TextConstruction("TOKENS"));
		production.addElement(new TokenConstruction("TokenDefinition",
				Quantity.EXPECT_MANY));
		getProductions().addRule(production);
	}

	private void addTokenDefinition() throws GrammarException {
		Production production = new Production("TokenDefinition");
		production.addElement(new TokenConstruction("IDENTIFIER"));
		production.addElement(new TokenConstruction("EQUALS"));
		production.addElement(new TokenConstruction("TokenConstruction",
				Quantity.EXPECT_MANY));
		production.addElement(new TokenConstruction("SEMICOLON"));
		getProductions().addRule(production);
	}

	private void addProductionsSection() throws GrammarException {
		Production production = new Production("Productions");
		production.addElement(new TextConstruction("PRODUCTIONS"));
		getProductions().addRule(production);
	}

	private void addTokenConstruction() throws GrammarException {
		Production production = new Production("TokenConstruction");
		production.addElement(new TokenConstruction("IDENTIFIER"));
		getProductions().addRule(production);

		production = new Production("TokenConstruction");
		production.addElement(new TokenConstruction("STRING_LITERAL"));
		getProductions().addRule(production);

	}
}
