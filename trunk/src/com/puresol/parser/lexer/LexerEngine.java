package com.puresol.parser.lexer;

import java.util.ArrayList;
import java.util.List;

import com.puresol.parser.tokens.Token;
import com.puresol.parser.tokens.TokenDefinition;
import com.puresol.parser.tokens.TokenPublicity;
import com.puresol.parser.tokens.TokenStream;
import com.puresol.utils.TextUtils;

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
	private final List<TokenDefinition> tokenDefinitions;
	private int currentLine = 1;
	private int currentPosition = 0;
	private int currentIndex = 0;

	private LexerEngine(TokenStream inputStream,
			List<TokenDefinition> tokenDefinitions) {
		this.inputStream = inputStream;
		this.tokenDefinitions = tokenDefinitions;
		outputStream = new TokenStream(inputStream.getFile());
	}

	private TokenStream process() throws NoMatchingTokenDefinitionFound {
		createOutputStream();
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
		List<Token> newTokens = TextLexerEngine.process(token.getText(),
				tokenDefinitions);
		for (Token newToken : newTokens) {
			addToken(newToken);
		}
	}

	private void processHiddenToken(Token token) {
		addToken(token);
	}

	private void processAddedToken(Token token) {
		addToken(token);
	}

	private void addToken(Token token) {
		outputStream.addToken(Token.createWithNewPositions(token, currentIndex,
				currentIndex, currentLine));
		currentLine += TextUtils.countLineBreaks(token.getText());
		currentPosition += token.getText().length();
		currentIndex++;
	}
}
