package com.puresol.uhura.parser;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.lexer.TokenStream;

public abstract class AbstractParser implements Parser {

	private static final long serialVersionUID = 6779683403706893973L;

	private final Grammar grammar;
	private TokenStream tokenStream = null;

	public AbstractParser(Grammar grammar) {
		super();
		this.grammar = grammar;
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

	/**
	 * @return the tokenStream
	 */
	protected final void setTokenStream(TokenStream tokenStream) {
		this.tokenStream = tokenStream;
	}
}
