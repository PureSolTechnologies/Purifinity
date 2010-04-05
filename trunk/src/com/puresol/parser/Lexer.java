package com.puresol.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.log4j.Logger;

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

	public Lexer(TokenStream stream) {
		this.inputStream = stream;
	}

	public Lexer(File directory, File file) throws FileNotFoundException,
			IOException {
		this.inputStream = new DefaultPreConditioner(directory, file)
				.getTokenStream();
	}

	public final void addDefinition(TokenDefinition definition) {
		tokenDefinitions.add(definition);
	}

	private <C> C createInstance(Class<C> clazz) throws LexerException {
		try {
			return Instances.createInstance(clazz);
		} catch (ClassInstantiationException e) {
			logger.error(e);
			throw new LexerException(e.getMessage());
		}
	}

	public final void addDefinition(Class<? extends TokenDefinition> definition)
			throws LexerException {
		tokenDefinitions.add(createInstance(definition));
	}

	public final void addDefinitions(
			Class<? extends TokenDefinitionGroup> definitions)
			throws LexerException {
		TokenDefinitionGroup definitionsInstance = createInstance(definitions);
		Collections.sort(definitionsInstance.getTokenDefinitions());
		tokenDefinitions.addAll(definitionsInstance.getTokenDefinitions());
	}

	public final void addDefinitions(ArrayList<TokenDefinition> definitions) {
		Collections.sort(definitions);
		tokenDefinitions.addAll(definitions);
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
		if (outputStream == null) {
			outputStream = LexerEngine.process(inputStream, tokenDefinitions);
		}
	}
}
