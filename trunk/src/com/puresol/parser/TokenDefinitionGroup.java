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

    public void addKeyword(Class<? extends TokenDefinition> keyword);

    public void addKeyword(TokenDefinition keyword);

    public ArrayList<TokenDefinition> getKeywords();

    public void addKeywords(Class<? extends TokenDefinitionGroup> keywords);

    public void addKeywords(TokenDefinitionGroup keywords);
}
