package com.puresoltechnologies.purifinity.framework.lang.c11.grammar;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import com.puresoltechnologies.parsers.impl.grammar.Grammar;
import com.puresoltechnologies.parsers.impl.grammar.GrammarException;
import com.puresoltechnologies.parsers.impl.grammar.GrammarManager;
import com.puresoltechnologies.parsers.impl.grammar.GrammarReader;
import com.puresoltechnologies.parsers.impl.lexer.Lexer;
import com.puresoltechnologies.parsers.impl.parser.Parser;
import com.puresoltechnologies.purifinity.framework.analysis.impl.AbstractLanguageGrammar;

public class C11Grammar extends AbstractLanguageGrammar {

	private static final long serialVersionUID = -8286230838527909083L;

	public static final String GRAMMAR_RESOURCE = "/com/puresoltechnologies/purifinity/framework/lang/c11/grammar/C11.g";
	public static final String PERSISTED_GRAMMAR_RESOURCE = GrammarManager
			.getPersistedGrammarPath(GRAMMAR_RESOURCE);
	public static final String PERSISTED_LEXER_RESOURCE = GrammarManager
			.getPersistedLexerPath(GRAMMAR_RESOURCE);
	public static final String PERSISTED_PARSER_RESOURCE = GrammarManager
			.getPersistedParserPath(GRAMMAR_RESOURCE);

	private static C11Grammar instance = null;
	private static Lexer lexer = null;
	private static Parser parser = null;

	public static C11Grammar getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		try {
			if (instance == null) {
				instance = new C11Grammar();
			}
		} catch (GrammarException e) {
			throw new RuntimeException("C11 Grammar is invalid!", e);
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
				grammar = restore(C11Grammar.class
						.getResourceAsStream(PERSISTED_GRAMMAR_RESOURCE));
			} catch (IOException e) {
				InputStream stream = C11Grammar.class
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

	private C11Grammar() throws GrammarException {
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
			throw new RuntimeException(
					"Could not restore class from input stream!", e);
		}
	}

	@Override
	public InputStream getGrammarDefinition() {
		return getClass().getResourceAsStream(GRAMMAR_RESOURCE);
	}
}
