package com.puresol.parser.tokens;

/**
 * This token definition is used for the last token within a token stream to
 * signal the end of the token stream. This is necessary for avoiding
 * ArrayOutOfBoundsExceptions and to have a clear end of token stream signal.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EndOfTokenStreamToken extends AbstractTokenDefinition {

	@Override
	protected void initialize() {
		/*
		 * this is an empty token and should not be used...
		 */
	}

}
