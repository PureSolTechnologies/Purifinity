package com.puresol.coding.lang.java.grammar;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.GrammarManager;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.parser.Parser;
import com.puresol.utils.PersistenceException;

/**
 * This class represents the Java programming language.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JavaGrammar extends Grammar {

    private static final long serialVersionUID = 7320197893887717979L;

    public static final String GRAMMAR_RESOURCE = "/com/puresol/coding/lang/java/grammar/Java-1.6.g";
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

    private static final Grammar grammar;
    static {
	try {
	    grammar = restore(
		    JavaGrammar.class
			    .getResourceAsStream(PERSISTED_GRAMMAR_RESOURCE),
		    Grammar.class);
	} catch (IOException e) {
	    throw new RuntimeException("Could not load grammar.", e);
	} catch (PersistenceException e) {
	    throw new RuntimeException("Could not load grammar.", e);
	}
    }

    private JavaGrammar() throws GrammarException {
	super(grammar.getOptions(), grammar.getTokenDefinitions(), grammar
		.getProductions());
    }

    public Lexer getLexer() throws IOException, PersistenceException {
	if (lexer == null) {
	    synchronized (this) {
		if (lexer == null) {
		    lexer = restore(
			    getClass().getResourceAsStream(
				    PERSISTED_LEXER_RESOURCE), Lexer.class);
		}
	    }
	}
	return lexer.clone();
    }

    public Parser getParser() throws IOException, PersistenceException {
	if (parser == null) {
	    synchronized (this) {
		if (parser == null) {
		    parser = restore(
			    getClass().getResourceAsStream(
				    PERSISTED_PARSER_RESOURCE), Parser.class);
		}
	    }
	}
	return parser.clone();
    }

    private static <T> T restore(InputStream inputStream, Class<T> clazz)
	    throws IOException, PersistenceException {
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
	    throw new PersistenceException(e);
	}
    }

    @Override
    public InputStream getGrammarDefinition() {
	return JavaGrammar.class.getResourceAsStream(GRAMMAR_RESOURCE);
    }
}
