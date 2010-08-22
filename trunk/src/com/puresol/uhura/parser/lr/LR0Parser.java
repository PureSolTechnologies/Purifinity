package com.puresol.uhura.parser.lr;

import java.util.Properties;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.parser.AbstractTableParser;

public class LR0Parser extends AbstractTableParser {

	public LR0Parser(Properties options, Grammar grammar)
			throws GrammarException {
		super(options, grammar);
	}

	@Override
	protected void calculateParserTable() throws GrammarException {
		setParserTable(new LR0ParserTable(getGrammar()));
	}

}
