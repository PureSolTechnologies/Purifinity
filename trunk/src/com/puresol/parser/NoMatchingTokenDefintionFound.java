package com.puresol.parser;

public class NoMatchingTokenDefintionFound extends Exception {

    private static final long serialVersionUID = -1406591512595275448L;

    public NoMatchingTokenDefintionFound(int line, int pos, String text) {
	super("line " + line + " (" + pos + "): " + text.substring(pos));
    }

}
