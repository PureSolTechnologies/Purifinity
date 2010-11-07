package com.puresol.uhura.parser;

import java.io.Serializable;

import com.puresol.uhura.ast.AST;
import com.puresol.uhura.lexer.TokenStream;

/**
 * This is the general interface for a parser. The parser is initialized via its
 * constructor. Only the token stream and the grammar is given.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Parser extends Serializable {

	/**
	 * This method starts the parsing process on the given token stream and
	 * returns a newly created result AST.
	 * 
	 * @param tokenStream
	 *            is the token stream to be parsed.
	 * @return A result AST is returned.
	 * @throws ParserException
	 */
	public AST parse(TokenStream tokenStream) throws ParserException;

}
