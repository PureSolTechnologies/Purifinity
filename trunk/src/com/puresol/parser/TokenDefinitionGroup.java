package com.puresol.parser;

import java.util.ArrayList;

/**
 * This is an interface for general purpose keyword lists for lexer and
 * parser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface TokenDefinitionGroup {

    public void addTokenDefinition(Class<? extends TokenDefinition> keyword)
	    throws TokenException;

    public void addTokenDefinition(TokenDefinition keyword);

    public ArrayList<TokenDefinition> getTokenDefinitions();

    public void addTokenDefinitions(
	    Class<? extends TokenDefinitionGroup> keywords)
	    throws TokenException;

    public void addTokenDefinitions(TokenDefinitionGroup keywords);
}
