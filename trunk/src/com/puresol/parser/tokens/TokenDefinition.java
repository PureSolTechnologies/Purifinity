package com.puresol.parser.tokens;

import java.util.regex.Pattern;

/**
 * This is a general interface for all token definitions used by Lexer.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface TokenDefinition {

    /**
     * Returns the start regexp pattern.
     * 
     * @return
     */
    public Pattern getStartPattern();

    /**
     * Returns the regexp pattern.
     * 
     * @return
     */
    public Pattern getPattern();

    /**
     * Returns the default publicity for this kind of token.
     * 
     * @return
     */
    public TokenPublicity getPublicity();

    /**
     * This method checks for an exact match of the regexp to the whole string.
     * 
     * @param string
     * @return
     */
    public boolean matches(String string);

    /**
     * This method checks for the the pattern included in the string.
     * 
     * @param string
     * @return
     */
    public boolean included(String string);

    /**
     * This method checks for the presence of the pattern at the beginning of
     * string.
     * 
     * @param string
     * @return
     */
    public boolean atStart(String string);

    /**
     * Returns the token at the beginning if matches.
     * 
     * @param string
     * @return
     */
    public String getTokenAtStart(String string);

    /**
     * Returns the token included in string if matches.
     * 
     * @param string
     * @return
     */
    public String getIncludedToken(String string);
}
