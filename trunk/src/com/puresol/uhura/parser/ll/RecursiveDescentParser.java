package com.puresol.uhura.parser.ll;

import java.util.Properties;

import com.puresol.uhura.ast.AST;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.parser.AbstractParser;
import com.puresol.uhura.parser.ParserException;

public class RecursiveDescentParser extends AbstractParser {

	public RecursiveDescentParser(Properties options, Grammar grammar) {
		super(options, grammar);
	}

	@Override
	public AST call() throws ParserException {
		// TODO Auto-generated method stub
		return null;
	}

}
