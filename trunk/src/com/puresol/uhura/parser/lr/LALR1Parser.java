package com.puresol.uhura.parser.lr;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;

public class LALR1Parser extends AbstractLRParser {

	private static final long serialVersionUID = -7605778449939543483L;

	public LALR1Parser(Grammar grammar) throws GrammarException {
		super(grammar);
	}

	@Override
	protected void calculateParserTable() throws GrammarException {
		setParserTable(new LALR1ParserTable(getGrammar()));
	}

}
