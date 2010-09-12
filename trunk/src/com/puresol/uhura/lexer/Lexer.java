package com.puresol.uhura.lexer;

import java.io.Reader;
import java.io.Serializable;

/**
 * This is the primary lexer interace. The lexer read
 * 
 * @author rludwig
 * 
 */
public interface Lexer extends Serializable {

	public TokenStream lex(Reader reader) throws LexerException;

	public TokenMetaInformation getMetaInformation();

}
