package com.puresol.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Lexer reads a preconditioned token stream and checks lexically tokens and
 * creates an extended token stream. This class manages all definitions and
 * calls a LexerEngine to scan the input token stream.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Lexer {

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

	public final void addDefinitions(TokenDefinitionGroup definitions) {
		List<TokenDefinition> definitionsList = definitions
				.getTokenDefinitions();
		Collections.sort(definitionsList);
		tokenDefinitions.addAll(definitionsList);
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
