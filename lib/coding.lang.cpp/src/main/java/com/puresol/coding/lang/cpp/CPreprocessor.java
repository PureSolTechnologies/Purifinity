package com.puresol.coding.lang.cpp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.preprocessor.Preprocessor;
import com.puresol.uhura.preprocessor.PreprocessorException;

/**
 * This class implements a C Preprocessor.
 * 
 * This class is not thread-safe due to handling of macro definitions!
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CPreprocessor implements Preprocessor {

    private static final Pattern pattern = Pattern.compile("^s*#");

    private final Map<String, String> definedMacros = new HashMap<String, String>();

    private static final Grammar grammar;
    static {
	try {
	    GrammarFile file = new GrammarFile(
		    CPreprocessor.class.getResourceAsStream("CPP.g"));
	    try {
		grammar = new GrammarConverter(file.getAST()).getGrammar();
	    } finally {
		file.close();
	    }
	} catch (IOException e) {
	    throw new RuntimeException(
		    "Could not initialize C Preprocessor grammar!", e);
	} catch (GrammarException e) {
	    throw new RuntimeException(
		    "Could not initialize C Preprocessor grammar!", e);
	}
    }

    private static final AnalyzerFactory analyzerFactory;
    static {
	try {
	    analyzerFactory = AnalyzerFactory.createFactory(grammar);
	} catch (GrammarException e) {
	    throw new RuntimeException(
		    "Could not initialize C Preprocessor AnalyzerFactory!", e);
	}
    }

    @Override
    public SourceCode process(SourceCode sourceCode)
	    throws PreprocessorException {
	try {
	    resetDefinedMacros();

	    Analyzer analyzer = analyzerFactory.createAnalyzer();

	    SourceCode preProcessedSourceCode = new SourceCode();
	    List<SourceCodeLine> source = sourceCode.getSource();

	    Iterator<SourceCodeLine> sourceIterator = source.iterator();
	    while (sourceIterator.hasNext()) {
		SourceCodeLine line = sourceIterator.next();
		Matcher matcher = pattern.matcher(line.getLine());
		if (matcher.find()) {
		    ParserTree ast = parse(analyzer, sourceIterator, line);
		    SourceCode newCode = process(ast);
		    preProcessedSourceCode.addSourceCode(newCode);
		} else {
		    preProcessedSourceCode.addSourceCodeLine(line);
		}
	    }
	    return preProcessedSourceCode;
	} catch (GrammarException e) {
	    throw new PreprocessorException(
		    "Could not pre-process source code!", e);
	}
    }

    private ParserTree parse(Analyzer analyzer,
	    Iterator<SourceCodeLine> sourceIterator, SourceCodeLine line)
	    throws PreprocessorException {
	ParserTree ast = null;
	SourceCode preprocessorCode = new SourceCode();
	preprocessorCode.addSourceCodeLine(line);
	try {
	    while (ast == null) {
		try {
		    ast = analyzer.analyze(preprocessorCode, line.getFile()
			    .getName());
		} catch (LexerException e) {
		    preprocessorCode.addSourceCodeLine(sourceIterator.next());
		} catch (ParserException e) {
		    preprocessorCode.addSourceCodeLine(sourceIterator.next());
		}
	    }
	} catch (NoSuchElementException e) {
	    throw new PreprocessorException(
		    "Could not pre-process source code due parsing issues in line "
			    + line.getFile() + ":" + line.getLineNumber()
			    + " \"" + line.getLine() + "\"!", e);
	}
	return ast;
    }

    private SourceCode process(ParserTree ast) {
	SourceCode code = new SourceCode();
	// TODO Auto-generated method stub
	return code;
    }

    private void resetDefinedMacros() {
	definedMacros.clear();

    }
}
