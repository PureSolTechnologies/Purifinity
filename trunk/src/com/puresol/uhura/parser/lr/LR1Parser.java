package com.puresol.uhura.parser.lr;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;

public class LR1Parser extends AbstractLRParser {

	private static final long serialVersionUID = 5550603885756798553L;

	public LR1Parser(Grammar grammar) throws GrammarException {
		super(grammar);
	}

	@Override
	protected void calculateParserTable() throws GrammarException {
		setParserTable(new LR1ParserTable(getGrammar()));
	}

}
