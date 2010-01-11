package com.puresol.parser;

public class NoMatchingTokenDefinitionFound extends Exception {

    private static final long serialVersionUID = -1406591512595275448L;

    public NoMatchingTokenDefinitionFound(int line, int pos, String text) {
	super("line " + line + " (" + pos + "): " + text.substring(pos));
    }

}
