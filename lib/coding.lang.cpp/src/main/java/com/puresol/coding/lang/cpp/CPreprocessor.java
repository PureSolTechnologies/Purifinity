package com.puresol.coding.lang.cpp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.trees.TreeException;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
import com.puresol.uhura.analyzer.Analyzer;
import com.puresol.uhura.analyzer.AnalyzerFactory;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarConverter;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.GrammarFile;
import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.LexerResult;
import com.puresol.uhura.lexer.SourceCode;
import com.puresol.uhura.lexer.SourceCodeLine;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenMetaData;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.preprocessor.Preprocessor;
import com.puresol.uhura.preprocessor.PreprocessorException;

/**
 * This class implements a C Preprocessor.
 * 
 * The implementation works in multiple steps:
 * 
 * 1) The Source Code is scanned and all lines starting with a sharp '#' are put
 * into the 'CPP-tokenizer' grammar to scan for a valid preprocessor statement.
 * If the first line does not match, we add the next lines until it fits (a
 * pass) or we run out of code (a fail). In case of a pass the tokens of the
 * part are taken and put into a TokenStream. Lines which are no preprocessor
 * statements are added into the {@link TokenStream} as ignored
 * "SourceCodeLine".
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

    private static final Logger logger = LoggerFactory
	    .getLogger(CPreprocessor.class);

    private static final String CPP_TOKENIZER_GRAMMAR_FILE = "CPP-tokenizer.g";
    private static final String CPP_GRAMMAR_FILE = "CPP.g";

    private static final Pattern pattern = Pattern.compile("^s*#");

    private static final IncludeDirectories includeDirectories = IncludeDirectories
	    .getInstance();

    private final Map<String, String> definedMacros = new HashMap<String, String>();

    private static final Grammar tokenizerGrammar;
    static {
	try {
	    GrammarFile file = new GrammarFile(
		    CPreprocessor.class
			    .getResourceAsStream(CPP_TOKENIZER_GRAMMAR_FILE));
	    try {
		tokenizerGrammar = new GrammarConverter(file.getAST())
			.getGrammar();
	    } finally {
		file.close();
	    }
	} catch (IOException e) {
	    throw new RuntimeException(
		    "Could not initialize C Preprocessor tokenizer grammar!", e);
	} catch (GrammarException e) {
	    throw new RuntimeException(
		    "Could not initialize C Preprocessor tokenizer grammar!", e);
	}
    }

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

    @Override
    public SourceCode process(File fileDirectory, SourceCode sourceCode)
	    throws PreprocessorException {
	resetDefinedMacros();
	TokenStream tokenStream = tokenize(sourceCode);
	ParserTree ast = parse(tokenStream);
	SourceCode preProcessedSourceCode = process(fileDirectory, ast);
	return preProcessedSourceCode;
    }

    private TokenStream tokenize(SourceCode sourceCode)
	    throws PreprocessorException {
	try {
	    TokenStream tokenStream = new TokenStream(
		    "TokenizedProprocessorStatement");
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
		    TokenMetaData metaData = new TokenMetaData(line.getFile()
			    .getPath(), tokenId, position,
			    line.getLineNumber(), 1);
		    Token token = new Token("SourceCodeLine", line.getLine(),
			    Visibility.IGNORED, metaData);
		    tokenStream.add(token);
		}
	    }
	    return tokenStream;
	} catch (GrammarException e) {
	    throw new PreprocessorException("Could not tokenize source code!",
		    e);
	}
    }

    private TokenStream tokenize(Analyzer analyzer,
	    Iterator<SourceCodeLine> sourceIterator, SourceCodeLine line)
	    throws PreprocessorException {
	final TokenStream tokenStream = new TokenStream(
		"PreprocessorStatementTokens");
	SourceCode preprocessorCode = new SourceCode();
	preprocessorCode.addSourceCodeLine(line);
	try {
	    ParserTree ast = null;
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
	    TreeWalker<ParserTree> walker = new TreeWalker<ParserTree>(ast);
	    walker.walk(new TreeVisitor<ParserTree>() {
		@Override
		public WalkingAction visit(ParserTree tree) {
		    Token token = tree.getToken();
		    if (token != null) {
			tokenStream.add(token);
		    }
		    return WalkingAction.PROCEED;
		}
	    });
	    return tokenStream;
	} catch (NoSuchElementException e) {
	    throw new PreprocessorException(
		    "Could not pre-process source code due parsing issues in line "
			    + line.getFile() + ":" + line.getLineNumber()
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

    private SourceCode process(final File fileDirectory, ParserTree ast) {
	final SourceCode code = new SourceCode();
	TreeWalker<ParserTree> walker = new TreeWalker<ParserTree>(ast);
	walker.walk(new TreeVisitor<ParserTree>() {

	    @Override
	    public WalkingAction visit(ParserTree tree) {
		Token token = tree.getToken();
		if (token == null) {
		    if ("IncludeMacro".equals(tree.getName())) {
			ParserTree includeString;
			try {
			    includeString = tree.getChild("StringLiteral");
			} catch (TreeException e) {
			    try {
				includeString = tree
					.getChild("FileIncludeLiteral");
			    } catch (TreeException e2) {
				throw new RuntimeException(
					"The IncludeMacro whether contains a StringLiteral nor a FileIncludeLiteral. The grammar or the implementation might be wrong!");
			    }
			}
			String includeFile = includeString.getToken().getText();
			try {
			    include(fileDirectory, code, includeFile);
			} catch (PreprocessorException e) {
			    logger.warn("Abort preprocessing of file!", e);
			    return WalkingAction.ABORT;
			}
		    }
		    /*
		     * TODO we need to implement the interpretation of the pre
		     * processor productions here...
		     */
		} else {
		    if ("SourceCodeLine".equals(token.getName())) {
			TokenMetaData metaData = token.getMetaData();
			SourceCodeLine sourceCodeLine = new SourceCodeLine(
				new File(metaData.getSourceName()), metaData
					.getLine(), token.getText());
			code.addSourceCodeLine(sourceCodeLine);
		    } else {
			/*
			 * We should not come here. A token is either a line of
			 * source code which is handled above, or we interpret
			 * the preprocessor statement into something else by
			 * node handling. But we should not run into a case
			 * where we need to process a token from a preprocessor
			 * statement singularly!
			 * 
			 * We can just abort now...
			 */
			return WalkingAction.ABORT;
		    }

		}
		return WalkingAction.PROCEED;
	    }
	});
	// TODO we need to add a check here how the walker finished its job...
	return code;
    }

    private void resetDefinedMacros() {
	definedMacros.clear();

    }

    private void include(File fileDirectory, SourceCode code, String includeFile)
	    throws PreprocessorException {
	boolean includeFileDirectory = false;
	if (includeFile.startsWith("\"") && includeFile.endsWith("\"")) {
	    includeFileDirectory = true;
	}
	includeFile = includeFile.substring(1, includeFile.length() - 1);
	if (includeFileDirectory) {
	    File file = new File(fileDirectory, includeFile);
	    if (file.exists()) {
		try {
		    SourceCode sourceCode = SourceCode.read(file);
		    code.addSourceCode(sourceCode);
		} catch (IOException e) {
		    throw new PreprocessorException("Could not include file '"
			    + file + "'!", e);
		}
	    }
	}
	for (File directory : includeDirectories.getDirectories()) {

	}
    }
}
