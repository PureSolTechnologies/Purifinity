package com.puresol.uhura.parser.lr;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;

public class LR0Parser extends AbstractLRTableParser {

	public LR0Parser(Grammar grammar) throws GrammarException {
		super(grammar);
	}

	@Override
	protected void calculateParserTable() throws GrammarException {
		setParserTable(new LR0ParserTable(getGrammar()));
	}

}
