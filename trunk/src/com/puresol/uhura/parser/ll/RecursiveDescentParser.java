package com.puresol.uhura.parser.ll;

import java.util.Properties;

import com.puresol.uhura.ast.SyntaxTree;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;

public class RecursiveDescentParser implements Parser {

	@SuppressWarnings("unused")
	private final Properties options;
	@SuppressWarnings("unused")
	private TokenStream tokenStream;
	@SuppressWarnings("unused")
	private ProductionSet ruleSet = null;

	public RecursiveDescentParser(Properties options) {
		this.options = options;
	}

	@Override
	public void setTokenStream(TokenStream tokenStream) {
		this.tokenStream = tokenStream;
	}

	@Override
	public void setProductions(ProductionSet ruleSet) {
		this.ruleSet = ruleSet;
	}

	@Override
	public SyntaxTree call() throws ParserException {
		return null;
	}

}
