package com.puresol.coding.lang.fortran.grammar;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.GrammarManager;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.parser.Parser;

public class FortranGrammar extends Grammar {

    private static final long serialVersionUID = -8286230838527909083L;

    public static final String GRAMMAR_RESOURCE = "/com/puresol/coding/lang/fortran/grammar/Fortran2008.g";
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
	    }
	} catch (GrammarException e) {
	    throw new RuntimeException("Fortran Grammar is invalid!", e);
	}
    }

    private static final Grammar grammar;
    static {
	try {
	    grammar = restore(FortranGrammar.class
		    .getResourceAsStream(PERSISTED_GRAMMAR_RESOURCE));
	} catch (IOException e) {
	    throw new RuntimeException("Could not load grammar.", e);
	}
    }

    private FortranGrammar() throws GrammarException {
	super(grammar.getOptions(), grammar.getTokenDefinitions(), grammar
		.getProductions());
    }

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
	return FortranGrammar.class.getResourceAsStream(GRAMMAR_RESOURCE);
    }

}
