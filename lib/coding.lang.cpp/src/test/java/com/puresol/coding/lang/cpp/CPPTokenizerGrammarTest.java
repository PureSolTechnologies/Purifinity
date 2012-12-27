package com.puresol.coding.lang.cpp;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.puresol.uhura.analyzer.Analyzer;
import com.puresol.uhura.analyzer.AnalyzerFactory;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarConverter;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.GrammarFile;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.preprocessor.PreprocessorException;
import com.puresol.uhura.source.SourceCode;

public class CPPTokenizerGrammarTest {

    private static Grammar grammar;
    private static AnalyzerFactory analyzerFactory;

    @BeforeClass
    public static void initialize() throws IOException, GrammarException {
	GrammarFile grammarFile = new GrammarFile(
		CPPTokenizerGrammarTest.class.getResourceAsStream("CPP.g"));
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

    private ParserTree checkParser(String... lines) throws LexerException,
	    ParserException, PreprocessorException {
	SourceCode sourceCode = SourceCode.fromStringArray(lines);
	ParserTree tree = analyzer.analyze(sourceCode, "Test");
	assertNotNull(tree);
	return tree;
    }

    @Test
    public void testError() throws Exception {
	checkParser("#error \"This is an error message!\"");
    }

    @Test
    public void testInclude() throws Exception {
	checkParser("#include <include.txt>");
    }

    @Test
    public void testLocalInclude() throws Exception {
	checkParser("#include \"include.txt\"");
    }

    @Test
    public void testElse() throws Exception {
	checkParser("#else");
    }

    @Test
    public void testEndIff() throws Exception {
	checkParser("#endif");
    }

    @Test
    public void testDefineMacroOnlyWithName() throws Exception {
	checkParser("#define NAME");
    }

    @Test
    public void testDefineObjectLikeMacroWithOneReplacement() throws Exception {
	checkParser("#define NAME replacement");
    }

    @Test
    public void testDefineObjectLikeMacroWithMultipleReplacements()
	    throws Exception {
	checkParser("#define NAME replacement1 replacement2 replacement3 replacement4");
    }

    @Test
    public void testDefineFunctionLikeMacroWithOneParameterAndWithOneReplacement()
	    throws Exception {
	checkParser("#define NAME(x) replacement1");
    }

    @Test
    public void testDefineFunctionLikeMacroWithOneParameterAndWithMultipleReplacements()
	    throws Exception {
	checkParser("#define NAME(x) replacement1 replacement2 replacement3 replacement4");
    }

    @Test
    public void testDefineFunctionLikeMacroWithMultipleParametersAndWithOneReplacement()
	    throws Exception {
	checkParser("#define NAME(x, y, z) replacement1");
    }

    @Test
    public void testDefineFunctionLikeMacroWithMultipleParametersAndWithMultipleReplacements()
	    throws Exception {
	checkParser("#define NAME(x, y, z) replacement1 replacement2 replacement3 replacement4");
    }

    @Test
    public void testDefineFunctionLikeMacroWithOptionalParametersOnly()
	    throws Exception {
	checkParser("#define NAME(...) replacement1");
    }

    @Test
    public void testDefineFunctionLikeMacroWithOneParameterAndOptionalParameters()
	    throws Exception {
	checkParser("#define NAME(x, ...) replacement1");
    }

    @Test
    public void testDefineFunctionLikeMacroWithMultipleParametersAndOptionalParameters()
	    throws Exception {
	checkParser("#define NAME(x, y, z, ...) replacement1");
    }

    @Test
    public void testDefineFunctionLikeMacroWithMultipleParametersOptionalParametersAndComplexReplacement()
	    throws Exception {
	checkParser("#define NAME(x, y, z, ...) fprintf(\"Hello x, y, z\"");
    }
}
