package com.puresol.uhura.parser;

import com.puresol.uhura.ast.AST;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.parsetable.ParserTable;

/**
 * This is the general interface for a parser. The parser is initialized via its
 * constructor. Only the token stream and the grammar is given.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Parser {

	public AST parse(TokenStream tokenStream) throws ParserException;

	public ParserTable getParserTable();

}
