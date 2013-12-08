package com.puresoltechnologies.purifinity.coding.lang.test.grammar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URL;

import com.puresoltechnologies.parsers.impl.grammar.Grammar;
import com.puresoltechnologies.parsers.impl.grammar.GrammarException;
import com.puresoltechnologies.parsers.impl.grammar.GrammarManager;
import com.puresoltechnologies.parsers.impl.grammar.GrammarReader;
import com.puresoltechnologies.parsers.impl.lexer.Lexer;
import com.puresoltechnologies.parsers.impl.parser.Parser;
import com.puresoltechnologies.purifinity.coding.lang.commons.AbstractLanguageGrammar;

/**
 * This class represents the Java programming language.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TestLanguageGrammar extends AbstractLanguageGrammar {

	private static final long serialVersionUID = 8411042156803619382L;

	public static final String GRAMMAR_RESOURCE = "/com/puresoltechnologies/purifinity/coding/lang/test/grammar/TestLanguage.g";
	static {
		URL grammarResource = TestLanguageGrammar.class
				.getResource(GRAMMAR_RESOURCE);
		if (grammarResource == null) {
			throw new RuntimeException("Could not find resource '"
					+ GRAMMAR_RESOURCE + "'!");
		}
	}

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
		try {
			if (instance == null) {
				instance = new TestLanguageGrammar();
			}
		} catch (GrammarException e) {
			throw new RuntimeException("Test Grammar is invalid!", e);
		}
	}

	private static Grammar grammar = null;

	public static Grammar getGrammar() {
		if (grammar == null) {
			initializeGrammar();
		}
		return grammar;
	}

	private static synchronized void initializeGrammar() {
		try {
			try {
				grammar = restore(TestLanguageGrammar.class
						.getResourceAsStream(PERSISTED_GRAMMAR_RESOURCE));
			} catch (IOException e) {
				InputStream stream = TestLanguageGrammar.class
						.getResourceAsStream(GRAMMAR_RESOURCE);
				try {
					GrammarReader reader = new GrammarReader(stream);
					try {
						grammar = reader.getGrammar();
					} finally {
						reader.close();
					}
				} finally {
					stream.close();
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(
					"Could neither read persisted grammar nor grammar itself.",
					e);
		} catch (GrammarException e) {
			throw new RuntimeException("Grammar is not valid.", e);
		}
	}

	private TestLanguageGrammar() throws GrammarException {
		super(getGrammar().getOptions(), getGrammar().getTokenDefinitions(),
				getGrammar().getProductions());
	}

	@Override
	public Lexer getLexer() throws IOException {
		if (lexer == null) {
			synchronized (this) {
				if (lexer == null) {
					lexer = (Lexer) restore(getClass().getResourceAsStream(
							PERSISTED_LEXER_RESOURCE));

				}
			}
		}
		return lexer.clone();
	}

	@Override
	public Parser getParser() throws IOException {
		if (parser == null) {
			synchronized (this) {
				if (parser == null) {
					parser = (Parser) restore(getClass().getResourceAsStream(
							PERSISTED_PARSER_RESOURCE));
				}
			}
		}
		return parser.clone();
	}

	private static <T> T restore(InputStream inputStream)
			throws FileNotFoundException, IOException {
		ObjectInputStream objectOutputStream = new ObjectInputStream(
				inputStream);
		try {
			@SuppressWarnings("unchecked")
			T t = (T) objectOutputStream.readObject();
			return t;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(
					"Could not restore class from input stream!", e);
		} finally {
			objectOutputStream.close();
		}
	}

	@Override
	public InputStream getGrammarDefinition() {
		return getClass().getResourceAsStream(GRAMMAR_RESOURCE);
	}
}
