package com.puresol.uhura.lexer;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.puresol.uhura.grammar.Grammar;

/**
 * This class creates a lexer based on options provided in a grammar file. This
 * is used to get the flexibility to used different lexers.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LexerFactory {

	private static final Logger logger = Logger.getLogger(LexerFactory.class);

	public static Lexer create(Grammar grammar) throws LexerFactoryException {
		try {
			Properties options = grammar.getOptions();
			if (options.containsKey("lexer")) {
				return (Lexer) Class.forName(options.getProperty("lexer"))
						.getConstructor(Grammar.class).newInstance(options);
			} else {
				return new RegExpLexer(grammar);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			throw new LexerFactoryException(e.getMessage());
		}
	}
}
