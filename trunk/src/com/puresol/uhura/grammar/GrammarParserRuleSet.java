package com.puresol.uhura.grammar;

import com.puresol.uhura.parser.ParserRule;
import com.puresol.uhura.parser.ParserRuleElement;
import com.puresol.uhura.parser.ParserRuleElementType;
import com.puresol.uhura.parser.ParserRuleSet;
import com.puresol.uhura.parser.Quantity;

/**
 * This class is a static representation for all parser rules used in Nyota
 * Uhura grammar files.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class GrammarParserRuleSet extends ParserRuleSet {

	public GrammarParserRuleSet() {
		super();
		addRules();
	}

	private void addRules() {
		addGrammar();
		addTokenDefinition();
		addOptions();
		addGrammarOptions();
		addHelper();
		addTokens();
		addProductions();
	}

	private void addGrammar() {
		ParserRule grammar = new ParserRule("grammar");
		grammar.addElement(new ParserRuleElement("grammar_options",
				ParserRuleElementType.PART));
		grammar.addElement(new ParserRuleElement("helper",
				ParserRuleElementType.PART));
		grammar.addElement(new ParserRuleElement("tokens",
				ParserRuleElementType.PART));
		grammar.addElement(new ParserRuleElement("productions",
				ParserRuleElementType.PART));
		addRule(grammar);
	}

	private void addGrammarOptions() {
		ParserRule grammarOptions = new ParserRule("grammar_options");
		grammarOptions.addElement(new ParserRuleElement("OPTIONS",
				ParserRuleElementType.TOKEN));
		addRule(grammarOptions);
	}

	private void addHelper() {
		ParserRule helper = new ParserRule("helper");
		helper.addElement(new ParserRuleElement("HELPER",
				ParserRuleElementType.TOKEN));
		addRule(helper);
	}

	private void addTokens() {
		ParserRule tokens = new ParserRule("tokens");
		tokens.addElement(new ParserRuleElement("TOKENS",
				ParserRuleElementType.TOKEN));
		addRule(tokens);
	}

	private void addProductions() {
		ParserRule productions = new ParserRule("productions");
		productions.addElement(new ParserRuleElement("PRODUCTIONS",
				ParserRuleElementType.TOKEN));
		addRule(productions);
	}

	private void addTokenDefinition() {
		ParserRule tokenDefinition = new ParserRule("TOKEN_DEFINITION");
		tokenDefinition.addElement(new ParserRuleElement("IDENTIFIER",
				ParserRuleElementType.TOKEN));
		tokenDefinition.addElement(new ParserRuleElement("EQUALS",
				ParserRuleElementType.TOKEN));
		tokenDefinition.addElement(new ParserRuleElement("STRING_LITERAL",
				ParserRuleElementType.TOKEN));
		tokenDefinition.addElement(new ParserRuleElement("options",
				ParserRuleElementType.PART, Quantity.ACCEPT));
		tokenDefinition.addElement(new ParserRuleElement("SEMICOLON",
				ParserRuleElementType.TOKEN));
		addRule(tokenDefinition);
	}

	private void addOptions() {
		ParserRule options = new ParserRule("options");
		options.addElement(new ParserRuleElement("COLON",
				ParserRuleElementType.TOKEN));
		options.addElement(new ParserRuleElement("IDENTIFIER",
				ParserRuleElementType.TOKEN));
		addRule(options);
	}
}
