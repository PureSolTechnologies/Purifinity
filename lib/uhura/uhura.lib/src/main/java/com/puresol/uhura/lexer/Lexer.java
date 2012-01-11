package com.puresol.uhura.lexer;

import java.io.Serializable;

/**
 * This is the primary lexer interace. The lexer read
 * 
 * @author rludwig
 * 
 */
public interface Lexer extends Serializable, Cloneable {

	public LexerResult lex(SourceCode sourceCode, String name)
			throws LexerException;

	public Lexer clone();

}