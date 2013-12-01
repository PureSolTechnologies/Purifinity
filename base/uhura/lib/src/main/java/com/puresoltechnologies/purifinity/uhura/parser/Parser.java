package com.puresoltechnologies.purifinity.uhura.parser;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import com.puresoltechnologies.purifinity.uhura.grammar.GrammarException;
import com.puresoltechnologies.purifinity.uhura.lexer.TokenStream;

/**
 * This is the general interface for a parser. The parser is initialized via its
 * constructor. Only the token stream and the grammar is given.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Parser extends Serializable, Cloneable {

    /**
     * This method starts the parsing process on the given token stream and
     * returns a newly created result AST.
     * 
     * @param tokenStream
     *            is the token stream to be parsed.
     * @return A result AST is returned.
     * @throws ParserException
     */
    public ParserTree parse(TokenStream tokenStream) throws ParserException;

    /**
     * This method is used to generated maximum detail information about the
     * parser and it's internal information.
     * 
     * @param directory
     *            is the directory to put the information into.
     * @throws IOException
     *             is thrown in cases of unexpected environment conditions like
     *             missing permissions for writing.
     * @throws GrammarException
     *             is thrown in cases of invalid grammars.
     */
    public void generateInspectionInformation(File directory)
	    throws IOException, GrammarException;

    public Parser clone();
}
