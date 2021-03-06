package com.puresoltechnologies.purifinity.server.plugin.java7.grammar;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import com.puresoltechnologies.parsers.grammar.Grammar;
import com.puresoltechnologies.parsers.grammar.GrammarException;
import com.puresoltechnologies.parsers.grammar.GrammarManager;
import com.puresoltechnologies.parsers.grammar.GrammarReader;
import com.puresoltechnologies.parsers.lexer.Lexer;
import com.puresoltechnologies.parsers.parser.Parser;
import com.puresoltechnologies.purifinity.analysis.spi.AbstractLanguageGrammar;

/**
 * This class represents the Java programming language.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JavaGrammar extends AbstractLanguageGrammar {

	private static final long serialVersionUID = 7320197893887717979L;

	public static final String GRAMMAR_RESOURCE = "/com/puresoltechnologies/purifinity/server/plugin/java7/grammar/Java-1.7.g";
	public static final String PERSISTED_GRAMMAR_RESOURCE = GrammarManager
			.getPersistedGrammarPath(GRAMMAR_RESOURCE);
	public static final String PERSISTED_LEXER_RESOURCE = GrammarManager
			.getPersistedLexerPath(GRAMMAR_RESOURCE);
	public static final String PERSISTED_PARSER_RESOURCE = GrammarManager
			.getPersistedParserPath(GRAMMAR_RESOURCE);

	private static JavaGrammar instance = null;

	private static Lexer lexer = null;
	private static Parser parser = null;

	public static JavaGrammar getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		try {
			if (instance == null) {
				instance = new JavaGrammar();
			}
		} catch (GrammarException e) {
			throw new RuntimeException("Java Grammar is invalid!", e);
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
				grammar = restore(JavaGrammar.class
						.getResourceAsStream(PERSISTED_GRAMMAR_RESOURCE));
			} catch (IOException e) {
				InputStream stream = JavaGrammar.class
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

	private JavaGrammar() throws GrammarException {
		super(getGrammar().getOptions(), getGrammar().getTokenDefinitions(),
				getGrammar().getProductions());
	}

	@Override
	public Lexer getLexer() throws IOException {
		if (lexer == null) {
			synchronized (this) {
				if (lexer == null) {
					lexer = restore(getClass().getResourceAsStream(
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
					parser = restore(getClass().getResourceAsStream(
							PERSISTED_PARSER_RESOURCE));
				}
			}
		}
		return parser.clone();
	}

	private static <T> T restore(InputStream inputStream) throws IOException {
		try {
			if (inputStream == null) {
				throw new IOException("Input stream is null!");
			}
			ObjectInputStream ois = new ObjectInputStream(inputStream);
			try {
				@SuppressWarnings("unchecked")
				T t = (T) ois.readObject();
				return t;
			} finally {
				ois.close();
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Could not restore from input stream!",
					e);
		}
	}

	@Override
	public InputStream getGrammarDefinition() {
		return getClass().getResourceAsStream(GRAMMAR_RESOURCE);
	}
}
