package com.puresol.uhura.lexer;

import java.io.Reader;

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
