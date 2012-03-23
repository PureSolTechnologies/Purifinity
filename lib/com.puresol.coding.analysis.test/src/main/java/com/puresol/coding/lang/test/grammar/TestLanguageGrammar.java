package com.puresol.coding.lang.test.grammar;

import java.io.IOException;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
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
public class TestLanguageGrammar extends Grammar {

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
	try {
	    if (instance == null) {
		instance = new TestLanguageGrammar();
	    }
	} catch (GrammarException e) {
	    throw new RuntimeException("Test Grammar is invalid!", e);
	}
    }

    private static final Grammar grammar;
    static {
	try {
	    grammar = (Grammar) Persistence.restore(TestLanguageGrammar.class
		    .getResourceAsStream(PERSISTED_GRAMMAR_RESOURCE));
	} catch (IOException e) {
	    throw new RuntimeException("Could not load grammar.", e);
	} catch (PersistenceException e) {
	    throw new RuntimeException("Could not load grammar.", e);
	}
    }

    private TestLanguageGrammar() throws GrammarException {
	super(grammar.getOptions(), grammar.getTokenDefinitions(), grammar
		.getProductions());
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
