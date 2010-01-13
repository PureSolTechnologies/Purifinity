package com.puresol.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;

public class Token {

    private static final Logger logger = Logger.getLogger(Token.class);

    private int tokenID;
    private int startPos;
    private int length;
    private TokenPublicity publicity;
    private String text;
    private int startLine;
    private int stopLine;
    private Class<? extends TokenDefinition> definition;

    public Token(int tokenID, TokenPublicity publicity, int startPos,
	    int length, String text, int startLine, int stopLine,
	    Class<? extends TokenDefinition> definition) {
	this.tokenID = tokenID;
	this.publicity = publicity;
	this.startPos = startPos;
	this.length = length;
	this.text = text;
	this.startLine = startLine;
	this.stopLine = stopLine;
	this.definition = definition;
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

    public TokenDefinition getDefinitionInstance() {
	try {
	    Constructor<? extends TokenDefinition> constructor =
		    definition.getConstructor();
	    return (TokenDefinition) constructor.newInstance();
	} catch (IllegalArgumentException e) {
	    logger.warn(e.getMessage(), e);
	} catch (InstantiationException e) {
	    logger.warn(e.getMessage(), e);
	} catch (IllegalAccessException e) {
	    logger.warn(e.getMessage(), e);
	} catch (InvocationTargetException e) {
	    logger.warn(e.getMessage(), e);
	} catch (SecurityException e) {
	    logger.warn(e.getMessage(), e);
	} catch (NoSuchMethodException e) {
	    logger.warn(e.getMessage(), e);
	}
	return null;
    }

    public String toString() {
	String output = String.valueOf(tokenID) + " ";
	if (stopLine != startLine) {
	    output += startLine + "-" + stopLine;
	} else {
	    output += String.valueOf(startLine);
	}
	output += " (" + startPos + "/" + length + "): '" + text + "'";
	if (definition != null) {
	    output += " (" + definition.getSimpleName() + ")";
	}
	if (publicity == TokenPublicity.HIDDEN) {
	    output += " (hidden!)";
	} else if (publicity == TokenPublicity.ADDED) {
	    output += " (added!)";
	}
	return output;
    }
}
