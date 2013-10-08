package com.puresol.purifinity.coding.lang.fortran2008.grammar;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import com.puresol.purifinity.coding.lang.c11.preprocessor.C11Preprocessor;
import com.puresol.purifinity.coding.lang.commons.AbstractLanguageGrammar;
import com.puresol.purifinity.uhura.grammar.Grammar;
import com.puresol.purifinity.uhura.grammar.GrammarException;
import com.puresol.purifinity.uhura.grammar.GrammarManager;
import com.puresol.purifinity.uhura.grammar.GrammarReader;
import com.puresol.purifinity.uhura.lexer.Lexer;
import com.puresol.purifinity.uhura.parser.Parser;

public class FortranGrammar extends AbstractLanguageGrammar {

	private static final long serialVersionUID = -8286230838527909083L;

	public static final String GRAMMAR_RESOURCE = "/com/puresol/purifinity/coding/lang/fortran2008/grammar/Fortran2008.g";
	public static final String PERSISTED_GRAMMAR_RESOURCE = GrammarManager
			.getPersistedGrammarPath(GRAMMAR_RESOURCE);
	public static final String PERSISTED_LEXER_RESOURCE = GrammarManager
			.getPersistedLexerPath(GRAMMAR_RESOURCE);
	public static final String PERSISTED_PARSER_RESOURCE = GrammarManager
			.getPersistedParserPath(GRAMMAR_RESOURCE);

	private static FortranGrammar instance = null;
	private static Lexer lexer = null;
	private static Parser parser = null;

	public static FortranGrammar getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		try {
			if (instance == null) {
				instance = new FortranGrammar();
				Class<?> clazz = C11Preprocessor.class;
				clazz.getName();
			}
		} catch (GrammarException e) {
			throw new RuntimeException("Fortran Grammar is invalid!", e);
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
				grammar = restore(FortranGrammar.class
						.getResourceAsStream(PERSISTED_GRAMMAR_RESOURCE));
			} catch (IOException e) {
				InputStream stream = FortranGrammar.class
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

	private FortranGrammar() throws GrammarException {
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
