package com.puresol.uhura.parser.lr;

import java.util.Properties;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.parser.AbstractTableParser;
import com.puresol.uhura.parser.ParserException;

public class SLR1Parser extends AbstractTableParser {

	public SLR1Parser(Properties options, Grammar grammar)
			throws ParserException {
		super(options, grammar);
	}

	@Override
	protected void calculateParserTable() throws ParserException {
		setParserTable(new SLR1ParserTable(getGrammar()));
	}

}
