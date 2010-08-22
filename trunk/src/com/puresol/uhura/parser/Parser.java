package com.puresol.uhura.parser;

import java.util.concurrent.Callable;

import com.puresol.uhura.ast.SyntaxTree;
import com.puresol.uhura.lexer.TokenStream;

/**
 * This is the general interface for a parser. The parser is initialized via its
 * constructor. Only the token stream and the grammar is given.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Parser extends Callable<SyntaxTree> {

	public void setTokenStream(TokenStream tokenStream);

	@Override
	public SyntaxTree call() throws ParserException;

}
