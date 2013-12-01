package com.puresoltechnologies.purifinity.uhura.parser.lr;

import java.io.File;
import java.io.IOException;

import com.puresoltechnologies.purifinity.uhura.grammar.Grammar;
import com.puresoltechnologies.purifinity.uhura.grammar.GrammarException;
import com.puresoltechnologies.purifinity.uhura.parser.parsetable.ParserTable;

public class SLR1Parser extends AbstractLRParser {

	private static final long serialVersionUID = -7605778449939543483L;

	public SLR1Parser(Grammar grammar) throws GrammarException {
		super(grammar);
	}

	@Override
	protected ParserTable calculateParserTable() throws GrammarException {
		return new SLR1ParserTable(getGrammar());
	}

	@Override
	public void generateInspectionInformation(File directory)
			throws IOException, GrammarException {
		getParserTable().generateInspectionInformation(directory);
	}

}
