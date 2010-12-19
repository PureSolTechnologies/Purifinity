package com.puresol.coding.lang.fortran.grammar;

import java.io.IOException;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarManager;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.parser.Parser;
import com.puresol.utils.Persistence;
import com.puresol.utils.PersistenceException;

public class FortranGrammar {

	public static final String GRAMMAR_RESOURCE = "/com/puresol/coding/lang/fortran/grammar/Fortran2008.g";
	public static final String PERSISTED_GRAMMAR_RESOURCE = GrammarManager
			.getPersistedGrammarPath(GRAMMAR_RESOURCE);
	public static final String PERSISTED_LEXER_RESOURCE = GrammarManager
			.getPersistedLexerPath(GRAMMAR_RESOURCE);
	public static final String PERSISTED_PARSER_RESOURCE = GrammarManager
			.getPersistedParserPath(GRAMMAR_RESOURCE);

	private static FortranGrammar instance = null;

	public static FortranGrammar getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new FortranGrammar();
		}
	}

	private FortranGrammar() {
		super();
	}

	public Grammar getGrammar() throws IOException, PersistenceException {
		return (Grammar) Persistence.restore(getClass().getResourceAsStream(
				PERSISTED_GRAMMAR_RESOURCE));
	}

	public Lexer getLexer() throws IOException, PersistenceException {
		return (Lexer) Persistence.restore(getClass().getResourceAsStream(
				PERSISTED_LEXER_RESOURCE));
	}

	public Parser getParser() throws IOException, PersistenceException {
		return (Parser) Persistence.restore(getClass().getResourceAsStream(
				PERSISTED_PARSER_RESOURCE));
	}
}
