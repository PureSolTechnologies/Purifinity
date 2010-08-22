package com.puresol.uhura.parser;

import java.util.Properties;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.lexer.TokenStream;

public abstract class AbstractParser implements Parser {

	private final Properties options;
	private final Grammar grammar;
	private TokenStream tokenStream = null;

	public AbstractParser(Properties options, Grammar grammar) {
		super();
		this.options = options;
		this.grammar = grammar;
	}

	@Override
	public final void setTokenStream(TokenStream tokenStream) {
		this.tokenStream = tokenStream;
	}

	/**
	 * @return the options
	 */
	protected final Properties getOptions() {
		return options;
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
