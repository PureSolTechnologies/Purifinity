package com.puresoltechnologies.parsers.impl.analyzer;

import com.puresoltechnologies.parsers.impl.grammar.Grammar;
import com.puresoltechnologies.parsers.impl.grammar.GrammarException;
import com.puresoltechnologies.parsers.impl.lexer.Lexer;
import com.puresoltechnologies.parsers.impl.lexer.LexerException;
import com.puresoltechnologies.parsers.impl.lexer.TokenStream;
import com.puresoltechnologies.parsers.impl.parser.Parser;
import com.puresoltechnologies.parsers.impl.parser.ParserException;
import com.puresoltechnologies.parsers.impl.parser.ParserTree;
import com.puresoltechnologies.parsers.impl.preprocessor.Preprocessor;
import com.puresoltechnologies.parsers.impl.preprocessor.PreprocessorException;
import com.puresoltechnologies.parsers.impl.source.SourceCode;

/**
 * This analyzer class bundles the functionality of an optional preprocessor, a
 * lexer and a parser to a complete analysis flow for easy usage of the parsing
 * functionality,
 * 
 * @author Rick-Rainer Ludwig
 */
public class Analyzer {

    private final Preprocessor preprocessor;
    private final Lexer lexer;
    private final Parser parser;

    public Analyzer(Grammar grammar, ClassLoader classLoader)
	    throws GrammarException {
	if (grammar.usesPreProcessor()) {
	    preprocessor = grammar.createPreprocessor(classLoader);
	} else {
	    preprocessor = null;
	}
	lexer = grammar.createLexer(classLoader);
	parser = grammar.createParser(classLoader);
    }

    public ParserTree analyze(SourceCode sourceCode) throws LexerException,
	    ParserException, PreprocessorException {
	SourceCode preProcessedSourceCode;
	if (preprocessor != null) {
	    preProcessedSourceCode = preprocessor.process(sourceCode);
	} else {
	    preProcessedSourceCode = sourceCode;
	}
	TokenStream tokenStream = lexer.lex(preProcessedSourceCode);
	return parser.parse(tokenStream);
    }

}
