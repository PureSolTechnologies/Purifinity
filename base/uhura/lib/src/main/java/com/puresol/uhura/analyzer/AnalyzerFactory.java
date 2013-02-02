package com.puresol.uhura.analyzer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.GrammarReader;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.preprocessor.Preprocessor;

/**
 * This class provides static members for the creation of analyzers out of
 * grammars. This factories use the grammar's information to create analyzers.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AnalyzerFactory {

    public static AnalyzerFactory createFactory(Grammar grammar)
	    throws GrammarException {
	return new AnalyzerFactory(grammar);
    }

    public static AnalyzerFactory createFactory(URL grammarURL)
	    throws GrammarException, IOException {
	InputStream stream = grammarURL.openStream();
	try {
	    GrammarReader reader = new GrammarReader(stream);
	    try {
		Grammar grammar = reader.getGrammar();
		return new AnalyzerFactory(grammar);
	    } finally {
		reader.close();
	    }
	} finally {
	    stream.close();
	}
    }

    private final Grammar grammar;

    public AnalyzerFactory(Grammar grammar) throws GrammarException {
	super();
	this.grammar = grammar;
    }

    public Grammar getGrammar() {
	return grammar;
    }

    public Preprocessor createPreProcessore() throws GrammarException {
	return grammar.createPreprocessor();
    }

    public Lexer createLexer() throws GrammarException {
	return grammar.createLexer();
    }

    public Parser createParser() throws GrammarException {
	return grammar.createParser();
    }

    public Analyzer createAnalyzer() throws GrammarException {
	return new Analyzer(grammar);
    }
}
