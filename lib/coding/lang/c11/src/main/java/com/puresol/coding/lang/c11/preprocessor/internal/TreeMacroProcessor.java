package com.puresol.coding.lang.c11.preprocessor.internal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.lang.c11.preprocessor.C11Preprocessor;
import com.puresol.coding.lang.c11.preprocessor.IncludeDirectories;
import com.puresol.trees.TreeException;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenMetaData;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.preprocessor.PreprocessorException;
import com.puresol.uhura.source.CodeLocation;
import com.puresol.uhura.source.SourceCode;
import com.puresol.uhura.source.SourceCodeLine;

/**
 * <pre>
 * This class processes a preprocessor AST and returns new source code for
 * compilation.
 * 
 * This object is not thread-safe due to a changing internal state during
 * processing.
 * 
 * An instance of this object is only called once meaningfully!
 * 
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TreeMacroProcessor implements TreeVisitor<ParserTree> {

    private static final String LINE_TERMINATOR_TOKEN_NAME = "LineTerminator";

    private static final String IDENTIFIER_TOKEN_NAME = "Identifier";

    private static final String INCLUDE_FILE_PRODUCTION_NAME = "IncludeFile";

    private static final Logger logger = LoggerFactory
	    .getLogger(TreeMacroProcessor.class);

    /**
     * This {@link IncludeDirectories} object contains the include directories
     * to be used with #include statements.
     */
    private final IncludeDirectories includeDirectories;

    /**
     * This field contains the information about the defined macros.
     */
    private final DefinedMacros definedMacros;
    /**
     * This is the token stream which is used and filled during processing.
     */
    private final TokenStream tokenStream = new TokenStream();
    /**
     * This field contains after processing the new source code.
     */
    private final SourceCode sourceCode = new SourceCode();
    /**
     * This field contains the original {@link ParserTree} of the source file
     * which is now to be processed.
     */
    private final ParserTree tree;
    /**
     * This field contains the current nesting depth. This is needed to check
     * for the maximum nesting depth to avoid recursive endless loops.
     */
    private final int nestingDepth;

    /**
     * This is the normal constructor to be used to process preprocessor source
     * trees.
     * 
     * @param tree
     *            is the {@link ParserTree} to be processed.
     * @param includeDirectories
     *            is the object which contains the include directories to be
     *            used during #include statements.
     * @param definedMacros
     *            is used to store defined macros for macro replacement.
     *            <b>Attention!!!:</b> This field is altered during run to be
     *            used later on in other instances due to newly defined macros!
     * @param nestingDepth
     *            is the current nesting depth which is used to check for the
     *            maximum nesting depth to avoid recursive endless loops.
     */
    public TreeMacroProcessor(ParserTree tree,
	    IncludeDirectories includeDirectories, DefinedMacros definedMacros,
	    int nestingDepth) {
	super();
	this.tree = tree;
	this.includeDirectories = includeDirectories;
	this.definedMacros = definedMacros;
	this.nestingDepth = nestingDepth;
    }

    /**
     * Runs the processor. The result of the process can be got by
     * {@link #getSourceCode()}.
     */
    public void process() {
	new TreeWalker<ParserTree>(tree).walk(this);
    }

    /**
     * This method returns the new source code after processing.
     * 
     * @return A new {@link SourceCode} object containing the process code is
     *         returned.
     */
    public SourceCode getSourceCode() {
	return sourceCode;
    }

    /**
     * This method walks over {@link #tree} and invokes the methods
     * {@link #processProduction(ParserTree)} and {@link #processToken(Token)}
     * to perform the actual processing tasks.
     */
    @Override
    public WalkingAction visit(ParserTree tree) {
	try {
	    Token token = tree.getToken();
	    if (token == null) {
		return processProduction(tree);
	    } else {
		processToken(token);
	    }
	    return WalkingAction.PROCEED;
	} catch (TreeException e) {
	    throw new RuntimeException(
		    "The grammar or the implementation might be wrong!", e);
	}
    }

    /**
     * This method processes a production and returns the next walking action
     * through the syntax tree.
     * 
     * @param tree
     * @return A {@link WalkingAction} is returned specifying the next action
     *         during tree walk.
     * @throws TreeException
     */
    private WalkingAction processProduction(ParserTree tree)
	    throws TreeException {
	if ("control-line".equals(tree.getName())) {
	    return processControlLine(tree);
	} else {
	    return WalkingAction.PROCEED;
	}
    }

    private WalkingAction processControlLine(ParserTree tree)
	    throws TreeException {
	try {
	    List<ParserTree> children = tree.getChildren();
	    ParserTree controlCommand = children.get(0);
	    if (controlCommand.getText().equals("#include")) {
		return include(tree);
	    } else if (controlCommand.getText().equals("#define")) {
		return define(tree);
	    } else {
		return WalkingAction.PROCEED;
	    }
	} catch (PreprocessorException e) {
	    logger.warn("Abort preprocessing of file!", e);
	    return WalkingAction.ABORT;
	}
    }

    private WalkingAction define(ParserTree tree) throws TreeException {
	List<ParserTree> children = tree.getChildren();
	if ((children.size() >= 4)
		&& (children.get(3).getName().equals("LPAREN"))) {
	    return defineFunctionLikeMacro(tree);

	} else {
	    return defineObjectLikeMacro(tree);
	}
    }

    /**
     * This method is invoked for includeFile nodes.
     * 
     * @param tree
     * @return
     * @throws TreeException
     * @throws PreprocessorException
     */
    private WalkingAction include(ParserTree tree) throws TreeException,
	    PreprocessorException {
	ParserTree headerName = tree.getChild("pp-tokens")
		.getChild("preprocessing-token").getChild("header-name");
	String includeFile = headerName.getText();
	/*
	 * We need to trim next due to we have looked for the production
	 * "IncludeFile" which may carry some white spaces.
	 */
	includeFile = includeFile.trim();
	logger.debug("Include file '" + includeFile + "',");
	performInclude(headerName.getToken().getMetaData().getSource(),
		includeFile);
	return WalkingAction.LEAVE_BRANCH;
    }

    private WalkingAction defineObjectLikeMacro(ParserTree tree)
	    throws TreeException {
	String macroName = tree.getChild(IDENTIFIER_TOKEN_NAME).getText();
	ParserTree replacementList = tree.getChild("ReplacementList");
	if (replacementList != null) {
	    TokenStream replacement = createReplacement(replacementList);
	    definedMacros.define(macroName, replacement);
	} else {
	    definedMacros.define(macroName);
	}
	return WalkingAction.LEAVE_BRANCH;
    }

    private WalkingAction defineFunctionLikeMacro(ParserTree tree)
	    throws TreeException {
	String macroName = tree.getChild(IDENTIFIER_TOKEN_NAME).getText();
	final List<String> parameters = new ArrayList<String>();
	ParserTree parameterList = tree.getChild("ParameterList");
	TreeVisitor<ParserTree> visitor = new TreeVisitor<ParserTree>() {
	    @Override
	    public WalkingAction visit(ParserTree tree) {
		Token token = tree.getToken();
		if (token != null) {
		    if (IDENTIFIER_TOKEN_NAME.equals(token.getName())) {
			parameters.add(token.getText());
		    } else if ("OptionalParameters".equals(token.getName())) {
			parameters.add("...");
		    }
		}
		return null;
	    }

	};
	new TreeWalker<ParserTree>(parameterList).walk(visitor);
	boolean optionalParameters = parameters.contains("...");
	if (optionalParameters) {
	    parameters.remove("...");
	}
	ParserTree replacementList = tree.getChild("ReplacementList");
	TokenStream replacement = createReplacement(replacementList);
	definedMacros.define(new Macro(macroName, replacement, parameters,
		optionalParameters));
	return WalkingAction.LEAVE_BRANCH;
    }

    private TokenStream createReplacement(ParserTree replacementList) {
	final TokenStream replacement = new TokenStream();
	TreeVisitor<ParserTree> visitor = new TreeVisitor<ParserTree>() {
	    @Override
	    public WalkingAction visit(ParserTree tree) {
		Token token = tree.getToken();
		if (token != null) {
		    if ((!"WhiteSpace".equals(token.getName()))
			    || (replacement.size() > 0)) {
			replacement.add(token);
		    }
		}
		return WalkingAction.PROCEED;
	    }
	};
	new TreeWalker<ParserTree>(replacementList).walk(visitor);
	return replacement;
    }

    /**
     * This method deals with the tokens in the tree. All tokens which are found
     * here are to be put into the ouput token stream. Exception are found macro
     * names which are replaced by there definitions.
     * 
     * @param token
     *            is the token to be processed.
     */
    private void processToken(Token token) {
	if (IDENTIFIER_TOKEN_NAME.equals(token.getName())) {
	    TokenStream tokenStream = replaceMactroAsNeeded(token);
	    tokenStream.addAll(tokenStream);
	} else {
	    tokenStream.add(token);
	    if (LINE_TERMINATOR_TOKEN_NAME.equals(token.getName())) {
		createNewSourceLine();
	    }
	}
    }

    /**
     * This method create a next new source code line after finding a new line
     * terminator during processing.
     * 
     * The current tokens of the {@link #tokenStream} are used to create a new
     * source code line and the token stream is cleared afterwards.
     */
    private void createNewSourceLine() {
	StringBuffer buffer = new StringBuffer();
	for (Token token : tokenStream) {
	    buffer.append(token.getText());
	}
	Token firstToken = tokenStream.get(0);
	TokenMetaData metaData = firstToken.getMetaData();
	SourceCodeLine newLine = new SourceCodeLine(metaData.getSource(),
		metaData.getLine(), buffer.toString());
	sourceCode.addSourceCodeLine(newLine);
	tokenStream.clear();
    }

    /**
     * This method replaces the macros in a given
     * 
     * @param text
     * @return
     */
    private TokenStream replaceMactroAsNeeded(Token token) {
	for (Macro macro : definedMacros.getMacros()) {
	    if (macro.getName().equals(token.getName())) {
		/*
		 * We found a matching macro.
		 */
		if (macro.getParameters().size() == 0) {
		    /*
		     * Process an object like macro.
		     */
		    return applyObjectLikeMacros(token, macro);
		} else {
		    /*
		     * Process an function like macro.
		     */
		    return applyFunctionLikeMacros(token, macro);
		}
	    }
	}
	/*
	 * We did not find a matching macro, so we put in here the original
	 * token and proceed.
	 */
	TokenStream tokenStream = new TokenStream();
	tokenStream.add(token);
	return tokenStream;
    }

    private TokenStream applyObjectLikeMacros(Token token, Macro macro) {
	return macro.getReplacement();
    }

    private TokenStream applyFunctionLikeMacros(Token token, Macro macro) {
	TokenStream tokenStream = new TokenStream();
	tokenStream.add(token);
	return tokenStream;
    }

    /**
     * This method performs the actual include.
     * 
     * @param source
     * @param includeFile
     * @throws PreprocessorException
     */
    private void performInclude(CodeLocation source, String includeFile)
	    throws PreprocessorException {
	if (nestingDepth == C11Preprocessor.getNestingLimit()) {
	    throw new PreprocessorException("Nesting limit for #include of "
		    + C11Preprocessor.getNestingLimit() + "is not sufficient!");
	}
	boolean includeFileDirectory = false;
	if (includeFile.startsWith("\"") && includeFile.endsWith("\"")) {
	    includeFileDirectory = true;
	}
	includeFile = includeFile.substring(1, includeFile.length() - 1);
	if (includeFileDirectory) {
	    CodeLocation includeSource = source.newRelativeSource(includeFile);
	    try {
		// read to be included source...
		SourceCode sourceCode = includeSource.load();
		// we need to process this source, too, before we can
		// include it...
		SourceCode processedSourceCode = new C11Preprocessor(
			includeDirectories, definedMacros, nestingDepth + 1)
			.process(sourceCode);
		// we actually do the including...
		this.sourceCode.addSourceCode(processedSourceCode);
	    } catch (IOException e) {
		throw new PreprocessorException(
			"Could not include file '"
				+ includeSource.getHumanReadableLocationString()
				+ "'!", e);
	    }
	}
	for (File directory : includeDirectories.getDirectories()) {
	    // TODO check the other directories if not successful, yet...
	}
    }

}
