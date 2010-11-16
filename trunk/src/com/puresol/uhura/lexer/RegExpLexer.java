package com.puresol.uhura.lexer;

import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.uhura.grammar.token.Visibility;

/**
 * This is a basic Lexer based on Java's regular expression engine.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class RegExpLexer implements Lexer {

	private static final long serialVersionUID = -6858518460147748314L;

	private static final Logger logger = Logger.getLogger(RegExpLexer.class);

	private static final String LINE_TERMINATOR = "(\\r\\n|\\n|\\r)";

	private final Grammar grammar;

	private TokenStream tokenStream = null;
	private StringBuilder text = null;

	public RegExpLexer(Grammar grammar) {
		this.grammar = grammar;
	}

	/**
	 * @return the grammar
	 */
	public Grammar getGrammar() {
		return grammar;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws LexerException
	 */

	@Override
	public TokenStream lex(Reader reader, String name) throws LexerException {
		readToString(reader);
		scan(name);
		return tokenStream;
	}

	private void readToString(Reader reader) throws LexerException {
		try {
			text = new StringBuilder();
			char chars[] = new char[4096];
			int len = 0;
			do {
				len = reader.read(chars);
				if (len > 0) {
					text.append(chars, 0, len);
				}
			} while (len == chars.length);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new LexerException(e.getMessage());
		}
	}

	private TokenStream scan(String name) throws LexerException {
		tokenStream = new TokenStream(name);
		int pos = 0;
		int id = 0;
		int line = 1;
		while (text.length() > 0) {
			Token token = findNextToken(text, id, pos, line);
			if ((token == null) || (token.getText().length() == 0)) {
				String exceptionText;
				if (text.length() <= 12) {
					exceptionText = text.toString();
				} else {
					exceptionText = text.substring(0, 12) + "...";

				}
				throw new LexerException("No token found for '" + exceptionText
						+ "' in line " + line + " at position " + pos + ".");
			}
			if (token.getVisibility() != Visibility.HIDDEN) {
				tokenStream.add(token);
			}
			id++;
			pos += token.getText().length();
			Pattern pattern = Pattern.compile(LINE_TERMINATOR);
			Matcher matcher = pattern.matcher(token.getText());
			if (matcher.matches()) {
				line++;
			} else if (matcher.find()) {
				int position = 0;
				while (matcher.find(position)) {
					line++;
					position = matcher.end();
				}
			}
			// forward...
			text = text.delete(0, token.getText().length());
		}
		return tokenStream;
	}

	private Token findNextToken(StringBuilder text, int id, int pos, int line) {
		Token nextToken = null;
		for (TokenDefinition definition : grammar.getTokenDefinitions()
				.getDefinitions()) {
			Matcher matcher = definition.getPattern().matcher(text);
			if (!matcher.find()) {
				continue;
			}
			String tokenText = matcher.group(0);
			if ((nextToken == null)
					|| (tokenText.length() > nextToken.getText().length())) {
				int lineCounter = 1;
				if (tokenText.contains("\r") || tokenText.contains("\n")) {
					lineCounter += tokenText.split("(\\r\\n|\\n|\\r)").length - 1;
				}
				TokenMetaData metaData = new TokenMetaData(
						tokenStream.getName(), id, pos, line, lineCounter);
				nextToken = new Token(definition.getName(), tokenText,
						definition.getVisibility(), metaData);
			}
		}
		return nextToken;
	}
}
