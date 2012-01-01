package com.puresol.coding.lang.test.grammar;

import java.io.IOException;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarManager;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.parser.Parser;
import com.puresol.utils.Persistence;
import com.puresol.utils.PersistenceException;

/**
 * This class represents the Java programming language.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TestLanguageGrammar {

	public static final String GRAMMAR_RESOURCE = "/com/puresol/coding/lang/test/grammar/TestLanguage.g";
	public static final String PERSISTED_GRAMMAR_RESOURCE = GrammarManager
			.getPersistedGrammarPath(GRAMMAR_RESOURCE);
	public static final String PERSISTED_LEXER_RESOURCE = GrammarManager
			.getPersistedLexerPath(GRAMMAR_RESOURCE);
	public static final String PERSISTED_PARSER_RESOURCE = GrammarManager
			.getPersistedParserPath(GRAMMAR_RESOURCE);

	private static TestLanguageGrammar instance = null;

	private static Lexer lexer = null;
	private static Parser parser = null;

	public static TestLanguageGrammar getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new TestLanguageGrammar();
		}
	}

	private Grammar grammar = null;

	private TestLanguageGrammar() {
		super();
	}

	public Grammar getGrammar() throws IOException, PersistenceException {
		if (grammar == null) {
			grammar = (Grammar) Persistence.restore(getClass()
					.getResourceAsStream(PERSISTED_GRAMMAR_RESOURCE));
		}
		return grammar;
	}

	public Lexer getLexer() throws IOException, PersistenceException {
		if (lexer == null) {
			synchronized (this) {
				if (lexer == null) {
					lexer = (Lexer) Persistence.restore(getClass()
							.getResourceAsStream(PERSISTED_LEXER_RESOURCE));

				}
			}
		}
		return lexer.clone();
	}

	public Parser getParser() throws IOException, PersistenceException {
		if (parser == null) {
			synchronized (this) {
				if (parser == null) {
					parser = (Parser) Persistence.restore(getClass()
							.getResourceAsStream(PERSISTED_PARSER_RESOURCE));
				}
			}
		}
		return parser.clone();
	}
}
