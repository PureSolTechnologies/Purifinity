package com.puresol.uhura.parser.ll;

import com.puresol.uhura.ast.AST;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.AbstractParser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.parsetable.ParserTable;

public class RecursiveDescentParser extends AbstractParser {

	private static final long serialVersionUID = 4214235112865305140L;

	public RecursiveDescentParser(Grammar grammar) {
		super(grammar);
	}

	@Override
	public AST parse(TokenStream tokenStream) throws ParserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParserTable getParserTable() {
		// TODO Auto-generated method stub
		return null;
	}

}
