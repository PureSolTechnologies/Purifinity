package com.puresol.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.puresol.exceptions.StrangeSituationException;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;

/**
 * Lexer reads a preconditioned token stream and checks lexically tokens and
 * creates an extended token stream. This class manages all definitions and
 * calls a LexerEngine to scan the input token stream.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Lexer {

    private static final Logger logger = Logger.getLogger(Lexer.class);

    private final TokenStream inputStream;
    private TokenStream outputStream = null;
    private final ArrayList<TokenDefinition> tokenDefinitions = new ArrayList<TokenDefinition>();

    public Lexer(TokenStream inputStream) {
	this.inputStream = inputStream;
    }

    public Lexer(File directory, File file) throws FileNotFoundException,
	    IOException {
	inputStream = new DefaultPreConditioner(directory, file)
		.getTokenStream();
    }

    public Lexer(String fileName, String text) {
	inputStream = new TokenStream(new File(fileName));
	inputStream.addToken(Token.createPrimitiveFromString(0, 0, 0, text));
    }

    public final void addDefinition(Class<? extends TokenDefinition> definition)
	    throws LexerException {
	try {
	    tokenDefinitions.add(Instances.createInstance(definition));
	} catch (ClassInstantiationException e) {
	    logger.error(e.getMessage(), e);
	    throw new LexerException("Could not instantiate '"
		    + definition.getName() + "'!");
	}
    }

    public final void addDefinitions(
	    List<Class<? extends TokenDefinition>> definitions)
	    throws LexerException {
	for (Class<? extends TokenDefinition> definition : definitions) {
	    addDefinition(definition);
	}
    }

    public final ArrayList<TokenDefinition> getDefinitions() {
	return tokenDefinitions;
    }

    public final TokenStream getTokenStream()
	    throws NoMatchingTokenDefinitionFound {
	if (outputStream == null) {
	    createOutputStream();
	}
	return outputStream;
    }

    private final synchronized void createOutputStream()
	    throws NoMatchingTokenDefinitionFound {
	try {
	    if (outputStream == null) {
		outputStream = LexerEngine.process(inputStream,
			tokenDefinitions);
		outputStream.lock();
	    }
	} catch (TokenException e) {
	    throw new StrangeSituationException("Could not lock token stream!");
	}
    }
}
