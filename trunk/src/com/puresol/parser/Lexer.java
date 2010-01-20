package com.puresol.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swingx.data.LineEnd;

import org.apache.log4j.Logger;

/**
 * Lexer reads a preconditioned token stream and checks lexically tokens and
 * creates an extended token stream.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Lexer {

	private static final Logger logger = Logger.getLogger(Lexer.class);

	private final TokenStream inputStream;
	private final TokenStream outputStream;
	private final ArrayList<TokenDefinition> tokenDefinitions = new ArrayList<TokenDefinition>();

	private int lineNumber;
	private int tokenId;
	private int streamPos;

	public Lexer(TokenStream stream) {
		this.inputStream = stream;
		outputStream = new TokenStream(inputStream.getFile());
		init();
	}

	public Lexer(File file) throws FileNotFoundException, IOException {
		this.inputStream = new DefaultPreConditioner(file).getTokenStream();
		outputStream = new TokenStream(inputStream.getFile());
		init();
	}

	private void init() {
		lineNumber = 0;
		tokenId = 0;
		streamPos = 0;
	}

	public final void addDefinition(TokenDefinition definition) {
		tokenDefinitions.add(definition);
	}

	public final void addDefinition(Class<? extends TokenDefinition> definition) {
		try {
			Constructor<?> constructor = definition.getConstructor();
			tokenDefinitions.add((TokenDefinition) constructor.newInstance());
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

	public final void addDefinitions(
			Class<? extends TokenDefinitionGroup> definitions) {
		try {
			Constructor<?> constructor = definitions.getConstructor();
			TokenDefinitionGroup definitionsInstance = (TokenDefinitionGroup) constructor
					.newInstance();
			Collections.sort(definitionsInstance.getTokenDefinitions());
			tokenDefinitions.addAll(definitionsInstance.getTokenDefinitions());
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

	public final void addDefinitions(ArrayList<TokenDefinition> definitions) {
		Collections.sort(definitions);
		tokenDefinitions.addAll(definitions);
	}

	public final ArrayList<TokenDefinition> getDefinitions() {
		return tokenDefinitions;
	}

	public final TokenStream getTokenStream()
			throws NoMatchingTokenDefinitionFound {
		if (outputStream.getSize() == 0) {
			createOutputStream();
		}
		return outputStream;
	}

	private void createOutputStream() throws NoMatchingTokenDefinitionFound {
		for (Token token : inputStream.getTokens()) {
			processToken(token);
		}
	}

	private void processToken(Token token)
			throws NoMatchingTokenDefinitionFound {
		if (token.getPublicity() == TokenPublicity.VISIBLE) {
			processVisibleToken(token);
		} else if (token.getPublicity() == TokenPublicity.HIDDEN) {
			processHiddenToken(token);
		} else if (token.getPublicity() == TokenPublicity.ADDED) {
			processAddedToken(token);
		}
	}

	private void processVisibleToken(Token token)
			throws NoMatchingTokenDefinitionFound {
		int tokenPos = 0;
		ArrayList<Token> newTokens = processVisibleToken(token, tokenPos,
				tokenId, lineNumber, streamPos);
		addTokens(newTokens);
	}

	private ArrayList<Token> processVisibleToken(Token token, int tokenPos,
			int tokenId, int lineNumber, int streamPos)
			throws NoMatchingTokenDefinitionFound {
		if (tokenPos == token.getLength() - 1) {
			return new ArrayList<Token>();
		}
		if (tokenPos > token.getLength() - 1) {
			throw new NoMatchingTokenDefinitionFound(inputStream.getFile(),
					lineNumber, tokenPos, token.getText());
		}
		for (TokenDefinition definition : tokenDefinitions) {
			ArrayList<Token> tokens = tryProcessDefinitionRecursively(
					definition, token, tokenPos, tokenId, lineNumber, streamPos);
			if (tokens != null) {
				return tokens;
			}
		}
		throw new NoMatchingTokenDefinitionFound(inputStream.getFile(),
				lineNumber, tokenPos, token.getText());
	}

	/**
	 * Is only useful for visible tokens!
	 * 
	 * @param definition
	 * @param token
	 * @param tokenPos
	 * @param tokenId
	 * @param lineNumber
	 * @param streamPos
	 * @return
	 * @throws NoMatchingTokenDefinitionFound
	 */
	private ArrayList<Token> tryProcessDefinitionRecursively(
			TokenDefinition definition, Token token, int tokenPos, int tokenId,
			int lineNumber, int streamPos)
			throws NoMatchingTokenDefinitionFound {
		String text = token.getText();
		if (!definition.atStart(text.substring(tokenPos))) {
			return null;
		}
		String tokenText = definition.getTokenAtStart(text.substring(tokenPos));
		if (logger.isTraceEnabled()) {
			logger.trace(inputStream.getFile() + ":" + lineNumber + ": "
					+ tokenText);
		}
		int numberOfLines = getNumberOfLines(tokenText);
		ArrayList<Token> newTokens = processVisibleToken(token, tokenPos
				+ tokenText.length(), tokenId + 1, lineNumber + numberOfLines,
				streamPos + tokenText.length());
		newTokens.add(0, new Token(tokenId, definition.getPublicity(),
				streamPos, tokenText.length(), tokenText, lineNumber,
				lineNumber + numberOfLines, definition.getClass()));
		return newTokens;
	}

	private void processHiddenToken(Token token) {
		int numberOfLines = getNumberOfLines(token.getText());
		addToken(new Token(tokenId, token.getPublicity(), streamPos, token
				.getText().length(), token.getText(), lineNumber, lineNumber
				+ numberOfLines, token.getDefinition()));
	}

	private void processAddedToken(Token token) {
		int numberOfLines = getNumberOfLines(token.getText());
		addToken(new Token(tokenId, token.getPublicity(), streamPos, token
				.getText().length(), token.getText(), lineNumber, lineNumber
				+ numberOfLines, token.getDefinition()));
	}

	private void addTokens(ArrayList<Token> tokens) {
		for (Token tokenToAdd : tokens) {
			addToken(tokenToAdd);
		}
	}

	private void addToken(Token token) {
		outputStream.addToken(token);
		tokenId++;
		lineNumber += token.getStopLine() - token.getStartLine();
		streamPos += token.getText().length();
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
