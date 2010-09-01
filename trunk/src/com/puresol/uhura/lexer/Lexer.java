package com.puresol.uhura.lexer;

import java.io.Reader;

/**
 * This is the primary lexer interace. The lexer read
 * 
 * @author rludwig
 * 
 */
public interface Lexer {

	public TokenStream lex(Reader reader) throws LexerException;

	public TokenMetaInformation getMetaInformation();

}
