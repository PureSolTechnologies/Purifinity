package com.puresol.uhura.parser;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.lexer.TokenStream;

public abstract class AbstractParser implements Parser {

	private final Grammar grammar;
	private TokenStream tokenStream = null;

	public AbstractParser(Grammar grammar) {
		super();
		this.grammar = grammar;
	}

	@Override
	public final void setTokenStream(TokenStream tokenStream) {
		this.tokenStream = tokenStream;
	}

	/**
	 * @return the grammar
	 */
	protected final Grammar getGrammar() {
		return grammar;
	}

	/**
	 * @return the tokenStream
	 */
	protected final TokenStream getTokenStream() {
		return tokenStream;
	}

}
