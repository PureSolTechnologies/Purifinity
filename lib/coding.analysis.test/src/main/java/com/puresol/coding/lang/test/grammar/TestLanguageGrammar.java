package com.puresol.coding.lang.test.grammar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.GrammarManager;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.parser.Parser;

/**
 * This class represents the Java programming language.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TestLanguageGrammar extends Grammar {

    private static final long serialVersionUID = 8411042156803619382L;

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
	    grammar = (Grammar) restore(TestLanguageGrammar.class
		    .getResourceAsStream(PERSISTED_GRAMMAR_RESOURCE));
	} catch (IOException e) {
	    throw new RuntimeException("Could not load grammar.", e);
	}
    }

    private TestLanguageGrammar() throws GrammarException {
	super(grammar.getOptions(), grammar.getTokenDefinitions(), grammar
		.getProductions());
    }

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

}