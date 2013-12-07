package com.puresoltechnologies.parser.impl.analyzer;

import com.puresoltechnologies.parser.impl.grammar.Grammar;
import com.puresoltechnologies.parser.impl.grammar.GrammarException;
import com.puresoltechnologies.parser.impl.lexer.Lexer;
import com.puresoltechnologies.parser.impl.lexer.LexerException;
import com.puresoltechnologies.parser.impl.lexer.TokenStream;
import com.puresoltechnologies.parser.impl.parser.Parser;
import com.puresoltechnologies.parser.impl.parser.ParserException;
import com.puresoltechnologies.parser.impl.parser.ParserTree;
import com.puresoltechnologies.parser.impl.preprocessor.Preprocessor;
import com.puresoltechnologies.parser.impl.preprocessor.PreprocessorException;
import com.puresoltechnologies.parser.impl.source.SourceCode;

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
