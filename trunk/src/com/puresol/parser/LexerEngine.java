package com.puresol.parser;

import java.util.ArrayList;

import javax.swingx.data.LineEnd;

/**
 * This is a separated lexer engine which lexically scans a token stream and
 * cuts it to tokens defined by an ArrayList of TokenDefinition.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LexerEngine {

	/**
	 * This is the only method to be used outside. This method produces the
	 * token stream which was lexically cut.
	 * 
	 * @param inputStream
	 *            the input stream to be scaned.
	 * @param tokenDefinitions
	 *            are the list of tokens to be scaned for.
	 * @return An output stream with all tokens is returned.
	 * @throws NoMatchingTokenDefinitionFound
	 */
	public static TokenStream process(TokenStream inputStream,
			ArrayList<TokenDefinition> tokenDefinitions)
			throws NoMatchingTokenDefinitionFound {
		return new LexerEngine(inputStream, tokenDefinitions).process();
	}

	private final TokenStream inputStream;
	private final TokenStream outputStream;
	private final ArrayList<TokenDefinition> tokenDefinitions;

	private int lineNumber;
	private int tokenId;
	private int streamPos;

	private LexerEngine(TokenStream inputStream,
			ArrayList<TokenDefinition> tokenDefinitions) {
		this.inputStream = inputStream;
		this.tokenDefinitions = tokenDefinitions;
		outputStream = new TokenStream(inputStream.getFile());
	}

	private TokenStream process() throws NoMatchingTokenDefinitionFound {
		init();
		createOutputStream();
		return outputStream;
	}

	private void init() {
		lineNumber = 0;
		tokenId = 0;
		streamPos = 0;
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
		ArrayList<Token> newTokens = TextLexerEngine.process(token.getText(),
				tokenDefinitions);
		for (Token newToken : newTokens) {
			addToken(newToken.getPublicity(), newToken.getText(), newToken
					.getDefinition());
		}
	}

	private void processHiddenToken(Token token) {
		addToken(token.getPublicity(), token.getText(), token.getDefinition());
	}

	private void processAddedToken(Token token) {
		addToken(token.getPublicity(), token.getText(), token.getDefinition());
	}

	private void addToken(TokenPublicity publicity, String text,
			Class<? extends TokenDefinition> definition) {
		addToken(new Token(tokenId, publicity, streamPos, text.length(), text,
				lineNumber, lineNumber + getNumberOfLines(text), definition));
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
