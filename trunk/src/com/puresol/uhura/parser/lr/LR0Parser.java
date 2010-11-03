package com.puresol.uhura.parser.lr;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.parser.parsetable.ParserTable;

public class LR0Parser extends AbstractLRParser {

	private static final long serialVersionUID = -8924244139827270797L;

	public LR0Parser(Grammar grammar) throws GrammarException {
		super(grammar);
	}

	@Override
	protected ParserTable calculateParserTable() throws GrammarException {
		return new LR0ParserTable(getGrammar());
	}

}
