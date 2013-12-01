package com.puresoltechnologies.purifinity.uhura.analyzer;

import com.puresoltechnologies.purifinity.uhura.grammar.Grammar;
import com.puresoltechnologies.purifinity.uhura.grammar.GrammarException;
import com.puresoltechnologies.purifinity.uhura.lexer.Lexer;
import com.puresoltechnologies.purifinity.uhura.lexer.LexerException;
import com.puresoltechnologies.purifinity.uhura.lexer.TokenStream;
import com.puresoltechnologies.purifinity.uhura.parser.Parser;
import com.puresoltechnologies.purifinity.uhura.parser.ParserException;
import com.puresoltechnologies.purifinity.uhura.parser.ParserTree;
import com.puresoltechnologies.purifinity.uhura.preprocessor.Preprocessor;
import com.puresoltechnologies.purifinity.uhura.preprocessor.PreprocessorException;
import com.puresoltechnologies.purifinity.uhura.source.SourceCode;

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
