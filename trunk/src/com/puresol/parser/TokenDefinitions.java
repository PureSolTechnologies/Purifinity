package com.puresol.parser;

import java.util.ArrayList;

/**
 * This is an interface for general purpose keyword lists for lexer and
 * parser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface TokenDefinitions {

    public void addKeyword(Class<? extends TokenDefinition> keyword);

    public void addKeyword(TokenDefinition keyword);

    public ArrayList<TokenDefinition> getKeywords();

    public void addKeywords(Class<? extends TokenDefinitions> keywords);

    public void addKeywords(TokenDefinitions keywords);
}
