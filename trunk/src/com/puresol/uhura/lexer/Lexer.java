package com.puresol.uhura.lexer;

import java.io.Reader;

import com.puresol.uhura.grammar.token.TokenDefinitionSet;

/**
 * This is the primary lexer interace. The lexer read
 * 
 * @author rludwig
 * 
 */
public interface Lexer {

	public void scan(Reader reader, TokenDefinitionSet rules)
			throws LexerException;

	public TokenStream getTokenStream();

	public TokenMetaInformation getMetaInformation();
}
