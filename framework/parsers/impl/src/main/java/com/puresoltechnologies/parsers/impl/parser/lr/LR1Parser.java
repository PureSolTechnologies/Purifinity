package com.puresoltechnologies.parsers.impl.parser.lr;

import java.io.File;
import java.io.IOException;

import com.puresoltechnologies.parsers.impl.grammar.Grammar;
import com.puresoltechnologies.parsers.impl.grammar.GrammarException;
import com.puresoltechnologies.parsers.impl.parser.parsetable.ParserTable;

public class LR1Parser extends AbstractLRParser {

	private static final long serialVersionUID = 5550603885756798553L;

	public LR1Parser(Grammar grammar) throws GrammarException {
		super(grammar);
	}

	@Override
	protected ParserTable calculateParserTable() throws GrammarException {
		return new LR1ParserTable(getGrammar());
	}

	@Override
	public void generateInspectionInformation(File directory)
			throws IOException, GrammarException {
		getParserTable().generateInspectionInformation(directory);
	}

}
