package com.puresoltechnologies.parsers.impl.lexer;

import java.io.Serializable;

import com.puresoltechnologies.parsers.impl.source.SourceCode;

/**
 * This is the primary lexer interace. The lexer read
 * 
 * @author rludwig
 * 
 */
public interface Lexer extends Serializable, Cloneable {

    public TokenStream lex(SourceCode sourceCode) throws LexerException;

    public Lexer clone();

}
