package com.puresol.coding.lang.cpp11;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.puresol.coding.lang.cpp11.internal.DefinedMacros;
import com.puresol.coding.lang.cpp11.internal.TokenCollector;
import com.puresol.coding.lang.cpp11.internal.TreeMacroProcessor;
import com.puresol.uhura.analyzer.Analyzer;
import com.puresol.uhura.analyzer.AnalyzerFactory;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarConverter;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.GrammarFile;
import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.LexerResult;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenMetaData;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.preprocessor.Preprocessor;
import com.puresol.uhura.preprocessor.PreprocessorException;
import com.puresol.uhura.source.Source;
import com.puresol.uhura.source.SourceCode;
import com.puresol.uhura.source.SourceCodeLine;

/**
 * This class implements a C Preprocessor. The reference implementation to test
 * agains is the GNU general-purpose preprocessor (GPP).
 * 
 * The implementation works in multiple steps:
 * 
 * 1) The Source Code is scanned and all lines starting with a sharp '#' are put
 * into the 'CPP.g' grammar to scan for a valid preprocessor statement. Only the
 * Macro production is used to parse the single line. If the first line does not
 * match, we add the next lines until it fits (a pass) or we run out of code (a
 * fail). In case of a pass the tokens of the part are taken and put into a
 * TokenStream. Lines which are no preprocessor statements are added into the
 * {@link TokenStream} as ignored "SourceCodeLine".
 * 
 * 2) The newly produced {@link TokenStream} is put into an analyzer of CPP
 * grammar to get the correct syntax tree.
 * 
 * 3) The syntax tree is processed than and the new source code is created.
 * 
 * This class is not thread-safe due to handling of macro definitions!
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CPreprocessor implements Preprocessor {

    private static final String CPP_GRAMMAR_FILE = "CPP.g";

    private static final Pattern pattern = Pattern.compile("^s*#");

    /**
     * This field contains the read grammar for the CPP.
     */
    private static final Grammar grammar;
    static {
	try {
	    GrammarFile file = new GrammarFile(
		    CPreprocessor.class.getResourceAsStream(CPP_GRAMMAR_FILE));
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

    /**
     * This field contains the tokenizer grammar, which is just a part of the
     * CPP grammar for parsing a single macro.
     */
    private static final Grammar tokenizerGrammar;
    static {
	try {
	    tokenizerGrammar = grammar.createWithNewStartProduction("Macro");
	} catch (GrammarException e) {
	    throw new RuntimeException(
		    "Could not initialize C Preprocessor tokenizer grammar from C preprocessor grammar!",
		    e);
	}
    }

    private static final AnalyzerFactory tokenizerAnalyzerFactory;
    static {
	try {
	    tokenizerAnalyzerFactory = AnalyzerFactory
		    .createFactory(tokenizerGrammar);
	} catch (GrammarException e) {
	    throw new RuntimeException(
		    "Could not initialize C Preprocessor tokenizerAnalyzerFactory!",
		    e);
	}
    }

    private static int nestingLimit = 16;

    /**
     * This is the setter for the {@link #nestingLimit} which controls the
     * maximum #include nesting depth.
     * 
     * @param nestingLimit
     *            is the number of allowed nested #include directives.
     */
    public static void setNestingLimit(int nestingLimit) {
	CPreprocessor.nestingLimit = nestingLimit;
    }

    /**
     * This is the getter for the {@link #nestingLimit} which controls the
     * maximum #include nesting depth.
     * 
     * @return The number of allowed nested #include directives is returned.
     */
    public static int getNestingLimit() {
	return nestingLimit;
    }

    private final DefinedMacros definedMacros = new DefinedMacros();

    private final int nestingDepth;

    public CPreprocessor() {
	this.nestingDepth = 0;
    }

    public CPreprocessor(int nestingDepth) {
	this.nestingDepth = nestingDepth;
    }

    @Override
    public SourceCode process(SourceCode sourceCode)
	    throws PreprocessorException {
	resetDefinedMacros();
	TokenStream tokenStream = tokenize(sourceCode);
	ParserTree ast = parse(tokenStream);
	SourceCode preProcessedSourceCode = process(ast);
	return preProcessedSourceCode;
    }

    private TokenStream tokenize(SourceCode sourceCode)
	    throws PreprocessorException {
	try {
	    TokenStream tokenStream = new TokenStream();
	    int tokenId = 0;
	    int position = 0;
	    Analyzer analyzer = tokenizerAnalyzerFactory.createAnalyzer();
	    List<SourceCodeLine> source = sourceCode.getSource();

	    Iterator<SourceCodeLine> sourceIterator = source.iterator();
	    while (sourceIterator.hasNext()) {
		SourceCodeLine line = sourceIterator.next();
		Matcher matcher = pattern.matcher(line.getLine());
		if (matcher.find()) {
		    TokenStream tokens = tokenize(analyzer, sourceIterator,
			    line);
		    tokenStream.addAll(tokens);
		} else {
		    TokenMetaData metaData = new TokenMetaData(
			    line.getSource(), tokenId, position,
			    line.getLineNumber(), 1);
		    Token token = new Token("SourceCodeLine", line.getLine(),
			    Visibility.VISIBLE, metaData);
		    tokenStream.add(token);
		}
	    }
	    TokenStream resultStream = new TokenStream();
	    resultStream.addAll(tokenStream);
	    return resultStream;
	} catch (GrammarException e) {
	    throw new PreprocessorException("Could not tokenize source code!",
		    e);
	}
    }

    private TokenStream tokenize(Analyzer analyzer,
	    Iterator<SourceCodeLine> sourceIterator, SourceCodeLine line)
	    throws PreprocessorException {
	SourceCode preprocessorCode = new SourceCode();
	preprocessorCode.addSourceCodeLine(line);
	Source currentSource = null;
	try {
	    ParserTree ast = null;
	    while (ast == null) {
		try {
		    Source source = line.getSource();
		    if (currentSource == null) {
			currentSource = source;
		    } else if (!currentSource.equals(source)) {
			throw new RuntimeException(
				"We found two different macros from two different files (first: "
					+ currentSource + "; second:" + source
					+ ")! This is not allowed!");
		    }
		    ast = analyzer.analyze(preprocessorCode);
		} catch (LexerException e) {
		    preprocessorCode.addSourceCodeLine(sourceIterator.next());
		} catch (ParserException e) {
		    preprocessorCode.addSourceCodeLine(sourceIterator.next());
		}
	    }
	    if (currentSource == null) {
		return new TokenStream();
	    }
	    return TokenCollector.collect(ast);
	} catch (NoSuchElementException e) {
	    throw new PreprocessorException(
		    "Could not pre-process source code due parsing issues in line "
			    + line.getSource() + ":" + line.getLineNumber()
			    + " \"" + line.getLine() + "\"!", e);
	}
    }

    private ParserTree parse(TokenStream tokenStream)
	    throws PreprocessorException {
	try {
	    Parser parser = grammar.createParser();
	    return parser.parse(new LexerResult(tokenStream));
	} catch (GrammarException e) {
	    throw new PreprocessorException(
		    "Cannot create parser for C preprocessor!", e);
	} catch (ParserException e) {
	    throw new PreprocessorException(
		    "Cannot create parse C preprocessor statement!", e);
	}
    }

    private SourceCode process(ParserTree ast) {
	TreeMacroProcessor processor = new TreeMacroProcessor(ast,
		definedMacros, nestingDepth);
	processor.process();
	return processor.getCode();
    }

    private void resetDefinedMacros() {
	definedMacros.reset();
    }

}
