package com.puresol.coding.lang.cpp;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.puresol.trees.TreeException;
import com.puresol.uhura.analyzer.Analyzer;
import com.puresol.uhura.analyzer.AnalyzerFactory;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarConverter;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.GrammarFile;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.SourceCode;
import com.puresol.uhura.lexer.SourceCodeLine;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.preprocessor.PreprocessorException;

public class CPPTokenizerGrammarTest {

    private static Grammar grammar;
    private static AnalyzerFactory analyzerFactory;

    @BeforeClass
    public static void initialize() throws IOException, GrammarException {
	GrammarFile grammarFile = new GrammarFile(
		CPPTokenizerGrammarTest.class.getResourceAsStream("CPP-tokenizer.g"));
	try {

	    GrammarConverter grammarConverter = new GrammarConverter(
		    grammarFile.getAST());
	    grammar = grammarConverter.getGrammar();
	    assertNotNull(grammar);
	    analyzerFactory = AnalyzerFactory.createFactory(grammar);
	} finally {
	    grammarFile.close();
	}
    }

    private Analyzer analyzer;

    @Before
    public void setup() throws GrammarException {
	analyzer = analyzerFactory.createAnalyzer();
    }

    @Test
    public void testInclude() throws IOException, TreeException,
	    GrammarException, LexerException, ParserException,
	    PreprocessorException {
	SourceCode sourceCode = new SourceCode();
	sourceCode.addSourceCodeLine(new SourceCodeLine(new File("Test"), 1,
		"#include <include.txt>"));
	assertNotNull(analyzer.analyze(sourceCode, "Test"));
    }

    @Test
    public void testLocalInclude() throws IOException, TreeException,
	    GrammarException, LexerException, ParserException,
	    PreprocessorException {
	SourceCode sourceCode = new SourceCode();
	sourceCode.addSourceCodeLine(new SourceCodeLine(new File("Test"), 1,
		"#include \"include.txt\""));
	assertNotNull(analyzer.analyze(sourceCode, "Test"));
    }

}
