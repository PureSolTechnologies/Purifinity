package com.puresoltechnologies.parsers.impl.parser.lr;

import java.io.File;
import java.io.IOException;

import com.puresoltechnologies.parsers.impl.grammar.Grammar;
import com.puresoltechnologies.parsers.impl.grammar.GrammarException;
import com.puresoltechnologies.parsers.impl.parser.parsetable.ParserTable;

public class LR0Parser extends AbstractLRParser {

	private static final long serialVersionUID = -8924244139827270797L;

	public LR0Parser(Grammar grammar) throws GrammarException {
		super(grammar);
	}

	@Override
	protected ParserTable calculateParserTable() throws GrammarException {
		return new LR0ParserTable(getGrammar());
	}

	@Override
	public void generateInspectionInformation(File directory)
			throws IOException, GrammarException {
		getParserTable().generateInspectionInformation(directory);
	}

}
