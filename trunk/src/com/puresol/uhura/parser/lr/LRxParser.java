package com.puresol.uhura.parser.lr;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.parser.parsetable.ParserTable;

public class LRxParser extends AbstractLRParser {

	private static final long serialVersionUID = -4877555726291037065L;

	private final ParserTable parserTable;

	public LRxParser(Grammar grammar, ParserTable parserTable)
			throws GrammarException {
		super(grammar);
		this.parserTable = parserTable;
	}

	@Override
	protected ParserTable calculateParserTable() throws GrammarException {
		return parserTable;
	}

}
