package com.puresol.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swingx.data.LineEnd;

import org.apache.log4j.Logger;

import com.puresol.exceptions.StrangeSituationException;

/**
 * Lexer reads a preconditioned token stream and checks lexically tokens
 * and creates an extended token stream.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Lexer {

    private static final Logger logger = Logger.getLogger(Lexer.class);

    private TokenStream inputStream = null;
    private TokenStream outputStream = null;
    private ArrayList<TokenDefinition> tokenDefinitions =
	    new ArrayList<TokenDefinition>();

    public Lexer(TokenStream stream) {
	this.inputStream = stream;
    }

    public void addDefinition(TokenDefinition definition) {
	tokenDefinitions.add(definition);
    }

    public void addDefinition(Class<? extends TokenDefinition> definition) {
	try {
	    Constructor<?> constructor = definition.getConstructor();
	    tokenDefinitions.add((TokenDefinition) constructor
		    .newInstance());
	} catch (SecurityException e) {
	    logger.error(e.getMessage(), e);
	} catch (NoSuchMethodException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalArgumentException e) {
	    logger.error(e.getMessage(), e);
	} catch (InstantiationException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalAccessException e) {
	    logger.error(e.getMessage(), e);
	} catch (InvocationTargetException e) {
	    logger.error(e.getMessage(), e);
	}
    }

    public void addDefinitions(
	    Class<? extends TokenDefinitionGroup> definitions) {
	try {
	    Constructor<?> constructor = definitions.getConstructor();
	    TokenDefinitionGroup definitionsInstance =
		    (TokenDefinitionGroup) constructor.newInstance();
	    tokenDefinitions.addAll(definitionsInstance.getKeywords());
	} catch (SecurityException e) {
	    logger.error(e.getMessage(), e);
	} catch (NoSuchMethodException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalArgumentException e) {
	    logger.error(e.getMessage(), e);
	} catch (InstantiationException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalAccessException e) {
	    logger.error(e.getMessage(), e);
	} catch (InvocationTargetException e) {
	    logger.error(e.getMessage(), e);
	}
    }

    public void addDefinitions(ArrayList<TokenDefinition> definitions) {
	tokenDefinitions.addAll(definitions);
    }

    public TokenStream getTokenStream()
	    throws NoMatchingTokenDefinitionFound {
	if (outputStream == null) {
	    createOutputStream();
	}
	return outputStream;
    }

    private void createOutputStream()
	    throws NoMatchingTokenDefinitionFound {
	outputStream =
		new TokenStream(inputStream.getName(), inputStream
			.getInputStream());
	int lineNumber = 0;
	for (Token token : inputStream.getTokens()) {
	    lineNumber = processToken(lineNumber, token);
	}
    }

    private int processToken(int lineNumber, Token token)
	    throws NoMatchingTokenDefinitionFound {
	try {
	    int id = outputStream.getSize();
	    int pos = 0;
	    String text = token.getText();
	    while (pos < text.length()) {
		boolean found = false;
		for (TokenDefinition definition : tokenDefinitions) {
		    if (definition.atStart(text.substring(pos))) {
			String tokenText =
				definition.getTokenAtStart(text
					.substring(pos));
			int numberOfLines = getNumberOfLines(tokenText);
			outputStream.addToken(new Token(token.getStream(),
				id, definition.getPublicity(), pos,
				tokenText.length(), tokenText, lineNumber,
				lineNumber + numberOfLines, definition
					.getClass()));
			id++;
			lineNumber += numberOfLines;
			pos += tokenText.length();
			found = true;
			break;
		    }
		}
		if (!found) {
		    throw new NoMatchingTokenDefinitionFound(lineNumber,
			    pos, text);
		}
	    }
	    return lineNumber;
	} catch (InvalidInputStreamException e) {
	    throw new StrangeSituationException(e);
	}
    }

    private int getNumberOfLines(String text) {
	int numberOfLines = 0;
	if (text.contains(LineEnd.UNIX.getString())) {
	    byte[] bytes = text.getBytes();
	    for (int index = 0; index < bytes.length; index++) {
		if ((char) bytes[index] == '\n') {
		    numberOfLines++;
		}
	    }
	}
	return numberOfLines;
    }
}
