package com.puresoltechnologies.parser.impl.analyzer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.puresoltechnologies.parser.impl.grammar.Grammar;
import com.puresoltechnologies.parser.impl.grammar.GrammarException;
import com.puresoltechnologies.parser.impl.grammar.GrammarReader;
import com.puresoltechnologies.parser.impl.lexer.Lexer;
import com.puresoltechnologies.parser.impl.parser.Parser;
import com.puresoltechnologies.parser.impl.preprocessor.Preprocessor;

/**
 * This class provides static members for the creation of analyzers out of
 * grammars. This factories use the grammar's information to create analyzers.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AnalyzerFactory {

    public static AnalyzerFactory createFactory(Grammar grammar,
	    ClassLoader classLoader) throws GrammarException {
	return new AnalyzerFactory(grammar, classLoader);
    }

    public static AnalyzerFactory createFactory(URL grammarURL,
	    ClassLoader classLoader) throws GrammarException, IOException {
	InputStream stream = grammarURL.openStream();
	try {
	    GrammarReader reader = new GrammarReader(stream);
	    try {
		Grammar grammar = reader.getGrammar();
		return new AnalyzerFactory(grammar, classLoader);
	    } finally {
		reader.close();
	    }
	} finally {
	    stream.close();
	}
    }

    private final ClassLoader classLoader;
    private final Grammar grammar;

    public AnalyzerFactory(Grammar grammar, ClassLoader classLoader)
	    throws GrammarException {
	super();
	this.grammar = grammar;
	this.classLoader = classLoader;
    }

    public Grammar getGrammar() {
	return grammar;
    }

    public Preprocessor createPreProcessore() throws GrammarException {
	return grammar.createPreprocessor(classLoader);
    }

    public Lexer createLexer() throws GrammarException {
	return grammar.createLexer(classLoader);
    }

    public Parser createParser() throws GrammarException {
	return grammar.createParser(classLoader);
    }

    public Analyzer createAnalyzer() throws GrammarException {
	return new Analyzer(grammar, classLoader);
    }
}
