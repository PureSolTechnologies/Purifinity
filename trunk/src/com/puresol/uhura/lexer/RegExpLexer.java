package com.puresol.uhura.lexer;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;
import java.util.regex.Matcher;

import org.apache.log4j.Logger;

import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;
import com.puresol.uhura.grammar.token.Visibility;

/**
 * This is a basic Lexer based on Java's regular expression engine.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class RegExpLexer implements Lexer {

	private static final Logger logger = Logger.getLogger(RegExpLexer.class);

	private final Properties options;
	private TokenDefinitionSet definitionSet = null;
	private TokenMetaInformation tokenMetaDatas = null;
	private TokenStream tokenStream = null;
	private String text = null;

	public RegExpLexer(Properties options) {
		this.options = options;
	}

	/**
	 * @return the rules
	 */
	public TokenDefinitionSet getRuleSet() {
		return definitionSet;
	}

	/**
	 * @return the options
	 */
	public Properties getOptions() {
		return options;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws LexerException
	 */
	@Override
	public void scan(Reader reader, TokenDefinitionSet rules)
			throws LexerException {
		this.definitionSet = rules;
		readToString(reader);
		scan();
	}

	private void readToString(Reader reader) throws LexerException {
		try {
			StringBuffer buffer = new StringBuffer();
			char chars[] = new char[4096];
			int len = 0;
			do {
				len = reader.read(chars);
				buffer.append(chars, 0, len);
			} while (len == 256);
			text = buffer.toString();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new LexerException(e.getMessage());
		}
	}

	private TokenStream scan() throws LexerException {
		tokenStream = new TokenStream();
		tokenMetaDatas = new TokenMetaInformation();
		int pos = 0;
		int id = 0;
		int line = 1;
		while (text.length() > 0) {
			Token token = findNextToken(text);
			if (token == null) {
				throw new LexerException("No token found for '"
						+ text.substring(0, 12) + "...' in line " + line
						+ " at position " + pos + ".");
			}
			if (token.getVisibility() != Visibility.HIDDEN) {
				tokenStream.add(token);
				// meta information...
				tokenMetaDatas.add(token, new TokenMetaData(token, id, pos,
						line));
			}
			id++;
			pos += token.getText().length();
			if (token.getText().contains("\n")
					|| token.getText().contains("\r")) {
				line += token.getText().split("(\\r\\n|\\n|\\r)").length - 1;
				if (token.getText().endsWith("\n")) {
					line += 2;
				}
			}
			// forward...
			text = text.substring(token.getText().length());
		}
		return tokenStream;
	}

	private Token findNextToken(String text) {
		Token nextToken = null;
		for (TokenDefinition definition : definitionSet.getRules()) {
			Matcher matcher = definition.getPattern().matcher(text);
			if (!matcher.find()) {
				continue;
			}
			if (matcher.start() != 0) {
				continue;
			}
			String group = matcher.group(0);
			if ((nextToken == null)
					|| (group.length() > nextToken.getText().length())) {
				nextToken = new Token(definition.getTypeId(), group,
						definition.getVisibility());
			}
		}
		return nextToken;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TokenStream getTokenStream() {
		return tokenStream;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TokenMetaInformation getMetaInformation() {
		return tokenMetaDatas;
	}

}
