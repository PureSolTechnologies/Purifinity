package com.puresol.parser;

import java.io.InputStream;

public class Token {

    private InputStream stream;
    private int tokenID;
    private int startPos;
    private int length;
    private TokenPublicity publicity;
    private String text;
    private int startLine;
    private int stopLine;
    private Class<? extends TokenDefinition> definition;

    public Token(InputStream stream, int tokenID,
	    TokenPublicity publicity, int startPos, int length,
	    String text, int startLine, int stopLine,
	    Class<? extends TokenDefinition> definition) {
	this.stream = stream;
	this.tokenID = tokenID;
	this.publicity = publicity;
	this.startPos = startPos;
	this.length = length;
	this.text = text;
	this.startLine = startLine;
	this.stopLine = stopLine;
	this.definition = definition;
    }

    public InputStream getStream() {
	return stream;
    }

    public int getTokenID() {
	return tokenID;
    }

    public int getStartPos() {
	return startPos;
    }

    public int getLength() {
	return length;
    }

    public TokenPublicity getPublicity() {
	return publicity;
    }

    public String getText() {
	return text;
    }

    public int getStartLine() {
	return startLine;
    }

    public int getStopLine() {
	return stopLine;
    }

    public Class<? extends TokenDefinition> getDefinition() {
	return definition;
    }

    public String toString() {
	String output = String.valueOf(tokenID) + " ";
	if (stopLine != startLine) {
	    output += startLine + "-" + stopLine;
	} else {
	    output += String.valueOf(startLine);
	}
	output += " (" + startPos + "/" + length + "): '" + text + "'";
	output += " (" + definition.getClass().getSimpleName() + ")";
	if (publicity == TokenPublicity.HIDDEN) {
	    output += " (hidden!)";
	} else if (publicity == TokenPublicity.ADDED) {
	    output += " (added!)";
	}
	return output;
    }
}
