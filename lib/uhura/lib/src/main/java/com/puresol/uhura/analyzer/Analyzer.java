package com.puresol.uhura.analyzer;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.LexerResult;
import com.puresol.uhura.lexer.SourceCode;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.preprocessor.Preprocessor;
import com.puresol.uhura.preprocessor.PreprocessorException;

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

    public Analyzer(Grammar grammar) throws GrammarException {
	if (grammar.usesPreProcessor()) {
	    preprocessor = grammar.createPreprocessor();
	} else {
	    preprocessor = null;
	}
	lexer = grammar.createLexer();
	parser = grammar.createParser();
    }

    public ParserTree analyze(SourceCode sourceCode, String name)
	    throws LexerException, ParserException, PreprocessorException {
	SourceCode preProcessedSourceCode;
	if (preprocessor != null) {
	    preProcessedSourceCode = preprocessor.process(sourceCode);
	} else {
	    preProcessedSourceCode = sourceCode;
	}
	LexerResult lexerResult = lexer.lex(preProcessedSourceCode, name);
	return parser.parse(lexerResult);
    }

}
