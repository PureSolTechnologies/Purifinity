package com.puresol.parser.tokens;

/**
 * This enum is for marking tokens for special channels for later
 * processing.Tokens within HIDDEN channel are usually ignored. ADDED is
 * meant for tokens which were artificically added to the token stream to
 * mark certain positions later used for processing.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum TokenPublicity {
    HIDDEN, VISIBLE, ADDED;
}
