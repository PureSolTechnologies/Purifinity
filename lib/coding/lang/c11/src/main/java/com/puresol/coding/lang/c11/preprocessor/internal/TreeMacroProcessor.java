package com.puresol.coding.lang.c11.preprocessor.internal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.lang.c11.C11ExpressionEvaluator;
import com.puresol.coding.lang.c11.preprocessor.C11Preprocessor;
import com.puresol.coding.lang.c11.preprocessor.DefinedMacros;
import com.puresol.coding.lang.c11.preprocessor.IncludeDirectories;
import com.puresol.trees.TreeException;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenMetaData;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.preprocessor.PreprocessorException;
import com.puresol.uhura.source.CodeLocation;
import com.puresol.uhura.source.SourceCode;
import com.puresol.uhura.source.SourceCodeLine;
import com.puresol.uhura.source.SourceFileLocation;
import com.puresol.uhura.ust.eval.EvaluationException;
import com.puresol.uhura.ust.eval.ValueTypeException;

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

    private static final String IDENTIFIER_TOKEN_NAME = "identifier";

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

    private PreprocessorException walkingResult = null;

    /**
     * This field is used to store token stream parts which are part of a
     * function-like macro replace which cannot be performed, yet due to its
     * spread of multiple lines.
     */
    private final TokenStream macroReplacementTokenStream = new TokenStream();

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
     * 
     * @throws PreprocessorException
     */
    public void process() throws PreprocessorException {
	walkingResult = null;
	new TreeWalker<ParserTree>(tree).walk(this);
	if (walkingResult != null) {
	    throw walkingResult;
	}
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
	    } else if (token.getName().equals("Comment")) {
		return processTextLine(tree);
	    } else {
		throw new RuntimeException(
			"An unprocessed token was found. This should not be possible!");
	    }
	} catch (TreeException e) {
	    throw new RuntimeException(
		    "The grammar or the implementation might be wrong!", e);
	} catch (PreprocessorException e) {
	    walkingResult = e;
	    return WalkingAction.ABORT;
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
     * @throws PreprocessorException
     */
    private WalkingAction processProduction(ParserTree tree)
	    throws TreeException, PreprocessorException {
	if ("control-line".equals(tree.getName())) {
	    return processControlLine(tree);
	} else if ("text-line".equals(tree.getName())) {
	    return processTextLine(tree);
	} else if ("if-section".equals(tree.getName())) {
	    return processIfSection(tree);
	} else if ("non-directive-line".equals(tree.getName())) {
	    /*
	     * As long as we do not know here what we need to do with this kind
	     * of line, we handle it as normal text-line. It is done so, too, by
	     * gpp.
	     */
	    return processTextLine(tree);
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
	    } else if (controlCommand.getText().equals("#undef")) {
		return undefine(tree);
	    } else {
		return WalkingAction.LEAVE_BRANCH;
	    }
	} catch (PreprocessorException e) {
	    logger.warn("Abort preprocessing of file!", e);
	    return WalkingAction.ABORT;
	}
    }

    /**
     * This method processes a single text-line.
     * 
     * @param tree
     * @return
     * @throws PreprocessorException
     */
    private WalkingAction processTextLine(ParserTree tree)
	    throws PreprocessorException {
	TokenCollector visitor = new TokenCollector();
	TreeWalker.walk(visitor, tree);
	TokenStream tokenStream = visitor.getTokenStream();
	macroReplacementTokenStream.addAll(tokenStream);
	/*
	 * We are in normal mode...
	 */
	if (replaceAllMacros(macroReplacementTokenStream)) {
	    TokenMetaData metaData = macroReplacementTokenStream.get(0)
		    .getMetaData();
	    StringBuffer buffer = new StringBuffer();
	    for (Token token : macroReplacementTokenStream) {
		buffer.append(token.getText());
	    }
	    int lineNum = 0;
	    int pos = 0;
	    while (pos < buffer.length()) {
		int index = buffer.indexOf("\n", pos);
		String line = buffer.substring(pos, index + 1);
		SourceCodeLine sourceCodeLine = new SourceCodeLine(
			metaData.getSource(), metaData.getLine() + lineNum,
			line);
		sourceCode.addSourceCodeLine(sourceCodeLine);
		lineNum++;
		pos += line.length();
	    }
	    macroReplacementTokenStream.clear();
	}
	return WalkingAction.LEAVE_BRANCH;
    }

    private boolean replaceAllMacros(TokenStream tokenStream)
	    throws PreprocessorException {
	for (int position = 0; position < tokenStream.size(); position++) {
	    if (!replaceMacroIfNeeded(tokenStream, position)) {
		return false;
	    }
	}
	return true;
    }

    private WalkingAction processIfSection(ParserTree ifSection)
	    throws TreeException, PreprocessorException {
	ParserTree ifGroup = ifSection.getChild("if-group");
	if (evaluateValidity(ifGroup)) {
	    TreeMacroProcessor processor = new TreeMacroProcessor(
		    ifGroup.getChild("group"), includeDirectories,
		    definedMacros, nestingDepth);
	    processor.process();
	    sourceCode.addSourceCode(processor.sourceCode);
	    return WalkingAction.LEAVE_BRANCH;
	}
	List<ParserTree> elifGroups = ifSection.getChildren("elif-group");
	for (ParserTree elifGroup : elifGroups) {
	    if (evaluateValidity(elifGroup)) {
		TreeMacroProcessor processor = new TreeMacroProcessor(
			elifGroup.getChild("group"), includeDirectories,
			definedMacros, nestingDepth);
		processor.process();
		sourceCode.addSourceCode(processor.sourceCode);
		return WalkingAction.LEAVE_BRANCH;
	    }
	}
	ParserTree elseGroup = ifSection.getChild("else-group");
	if (elseGroup != null) {
	    TreeMacroProcessor processor = new TreeMacroProcessor(
		    elseGroup.getChild("group"), includeDirectories,
		    definedMacros, nestingDepth);
	    processor.process();
	    sourceCode.addSourceCode(processor.sourceCode);
	}
	return WalkingAction.LEAVE_BRANCH;
    }

    private boolean evaluateValidity(ParserTree evaluation)
	    throws TreeException, PreprocessorException {
	if (evaluation.hasChild("PP_IFDEF")) {
	    ParserTree identifier = evaluation.getChild("identifier");
	    if (definedMacros.isDefined(identifier.getText())) {
		return true;
	    }
	} else if (evaluation.hasChild("PP_IFNDEF")) {
	    ParserTree identifier = evaluation.getChild("identifier");
	    if (!definedMacros.isDefined(identifier.getText())) {
		return true;
	    }
	} else if (evaluation.hasChild("PP_IF")
		|| evaluation.hasChild("PP_ELIF")) {
	    // #if and #elif
	    ParserTree expression = evaluation.getChild("constant-expression");
	    C11ExpressionEvaluator evaluator = new C11ExpressionEvaluator(
		    expression);
	    try {
		evaluator.evaluate();
		return evaluator.getResult().getBooleanValue();
	    } catch (EvaluationException e) {
		throw new PreprocessorException(
			"Could not evaluate expression '"
				+ expression.toString() + "'.", e);
	    } catch (ValueTypeException e) {
		throw new PreprocessorException(
			"Could not evaluate expression '"
				+ expression.toString() + "'.", e);
	    }
	} else {
	    throw new RuntimeException("Illegal evaluation production found!");
	}
	return false;
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

    private WalkingAction undefine(ParserTree tree) throws TreeException {
	ParserTree identifier = tree.getChild("identifier");
	definedMacros.undefine(identifier.getText());
	return WalkingAction.LEAVE_BRANCH;
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
	TokenStream replacement = createReplacement(tree);
	definedMacros.define(macroName, replacement);
	return WalkingAction.LEAVE_BRANCH;
    }

    private WalkingAction defineFunctionLikeMacro(ParserTree tree)
	    throws TreeException {
	String macroName = tree.getChild(IDENTIFIER_TOKEN_NAME).getText();
	final List<String> parameters = new ArrayList<String>();
	ParserTree parameterList = tree.getChild("identifier-list");
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
	TreeWalker.walk(visitor, parameterList);
	boolean optionalParameters = parameters.contains("...");
	if (optionalParameters) {
	    parameters.remove("...");
	}
	TokenStream replacement = createReplacement(tree);
	Macro macro = new Macro(macroName, replacement, parameters,
		optionalParameters);
	definedMacros.define(macro);
	return WalkingAction.LEAVE_BRANCH;
    }

    private TokenStream createReplacement(ParserTree defineStatement)
	    throws TreeException {
	final TokenStream replacement = new TokenStream();
	/*
	 * Check for relevant white spaces...
	 */
	List<ParserTree> defineChildren = defineStatement.getChildren();
	int startIndex;
	if (defineStatement.hasChild("RPAREN")) {
	    ParserTree rparenToken = defineStatement.getChild("RPAREN");
	    startIndex = defineChildren.indexOf(rparenToken) + 1;
	} else {
	    ParserTree identifierToken = defineStatement
		    .getChild(IDENTIFIER_TOKEN_NAME);
	    startIndex = defineChildren.indexOf(identifierToken) + 1;
	}
	boolean skipedWhiteSpace = false;
	for (int i = startIndex; i < defineChildren.size(); i++) {
	    ParserTree child = defineChildren.get(i);
	    if (child.getName().equals("replacement-list")) {
		break;
	    }
	    Token token = child.getToken();
	    if (token.getName().equals("LineTerminator")) {
		break;
	    }
	    if (token.getName().equals("WhiteSpace") && (!skipedWhiteSpace)) {
		skipedWhiteSpace = true;
		continue;
	    }
	    if (token.getName().equals("LineConcatenation")) {
		replacement.add(new Token("LineTerminator", "\n",
			Visibility.HIDDEN, token.getMetaData()));
	    } else {
		replacement.add(token);
	    }
	}
	/*
	 * Get replacement list.
	 */
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
	ParserTree replacementList = defineStatement
		.getChild("replacement-list");
	TreeWalker.walk(visitor, replacementList);
	return replacement;
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
	boolean includeFileDirectory = isFileDirectoryInclude(includeFile);
	includeFile = includeFile.substring(1, includeFile.length() - 1);
	if (includeFileDirectory) {
	    CodeLocation includeSource = source.newRelativeSource(includeFile);
	    if (includeFile(includeSource)) {
		return;
	    }
	}
	for (File directory : includeDirectories.getDirectories()) {
	    File file = new File(directory, includeFile);
	    if (includeFile(new SourceFileLocation(file))) {
		return;
	    }
	}
    }

    /**
     * Performs an include for a given source.
     * 
     * @param includeSource
     * @throws PreprocessorException
     */
    private boolean includeFile(CodeLocation includeSource)
	    throws PreprocessorException {
	try {
	    if (!includeSource.isAvailable()) {
		return false;
	    }
	    // read to be included source...
	    SourceCode sourceCode = includeSource.load();
	    // we need to process this source, too, before we can
	    // include it...
	    SourceCode processedSourceCode = new C11Preprocessor(
		    includeDirectories, definedMacros, nestingDepth + 1)
		    .process(sourceCode);
	    // we actually do the including...
	    this.sourceCode.addSourceCode(processedSourceCode);
	    return true;
	} catch (IOException e) {
	    throw new PreprocessorException("Could not include file '"
		    + includeSource.getHumanReadableLocationString() + "'!", e);
	}
    }

    /**
     * Checks whether an include file is a local directory include or a system
     * include.
     * 
     * @param includeFile
     *            is the file string with greater and smaller than signs or
     *            double quotes. It is checked for the delimiting signs.
     * @return True is returned if it is a local include file.
     */
    private boolean isFileDirectoryInclude(String includeFile) {
	if (includeFile.startsWith("\"") && includeFile.endsWith("\"")) {
	    return true;
	}
	return false;
    }

    /**
     * This method replaces the macros in a given
     * 
     * @param text
     * @return
     * @throws PreprocessorException
     */
    private boolean replaceMacroIfNeeded(TokenStream tokenStream, int position)
	    throws PreprocessorException {
	for (int i = position; i < tokenStream.size(); i++) {
	    Token token = tokenStream.get(i);
	    String text = token.getText();
	    if (definedMacros.isDefined(text)) {
		Macro macro = definedMacros.getMacro(text);
		/*
		 * We found a matching macro.
		 */
		if (macro.getParameters().size() == 0) {
		    /*
		     * Process an object like macro.
		     */
		    replaceObjectLikeMacro(tokenStream, i, macro);
		    return true;
		} else {
		    /*
		     * Process an function like macro.
		     */
		    return replaceFunctionLikeMacro(tokenStream, i, macro);
		}
	    }
	}
	return true;
    }

    /**
     * <p>
     * This replacement is very simple. The actual token of the macro name is
     * replaced with the replacement list of a macro without parameters. Even if
     * a parameter list would follow, this is ignored and only the macro name is
     * relevant.
     * </p>
     * <p>
     * This behavior was checked with gpp.
     * </p>
     * 
     * @param tokenStream
     * @param tokenId
     * @param macro
     * @throws PreprocessorException
     */
    private void replaceObjectLikeMacro(TokenStream tokenStream, int tokenId,
	    Macro macro) throws PreprocessorException {
	Token token = tokenStream.get(tokenId);
	TokenStream definition = macro.getReplacement();
	TokenStream replacement = new TokenStream();
	// Add all tokens before macro
	for (int i = 0; i < tokenId; i++) {
	    replacement.add(tokenStream.get(i));
	}
	// Add replacement instead of macro name
	for (Token defToken : definition) {
	    Token replacementToken = new Token(defToken.getName(),
		    defToken.getText(), defToken.getVisibility(),
		    token.getMetaData());
	    replacement.add(replacementToken);
	}
	// Add all tokens behind macro
	for (int i = tokenId + 1; i < tokenStream.size(); i++) {
	    replacement.add(tokenStream.get(i));
	}
	replaceAllMacros(replacement);
	tokenStream.clear();
	tokenStream.addAll(replacement);
    }

    private boolean replaceFunctionLikeMacro(TokenStream tokenStream,
	    int tokenId, Macro macro) throws PreprocessorException {
	ReplacementParameterResult parameterReplacements = ReplacementParameterResult
		.extractParameterReplacements(tokenStream, tokenId);
	if (parameterReplacements == null) {
	    return false;
	}
	TokenStream replacement = new TokenStream();
	// Add all tokens before macro
	for (int i = 0; i < tokenId; i++) {
	    replacement.add(tokenStream.get(i));
	}
	// Add replacement instead of macro name
	Token token = tokenStream.get(tokenId);
	List<String> parameters = macro.getParameters();
	TokenStream replacementDefinition = macro.getReplacement();
	for (Token defToken : replacementDefinition) {
	    int foundParameterId = findParameterId(parameters, defToken);
	    if (foundParameterId >= 0) {
		replacement.addAll(parameterReplacements
			.getReplacement(foundParameterId));
	    } else {
		Token replacementToken = new Token(defToken.getName(),
			defToken.getText(), defToken.getVisibility(),
			token.getMetaData());
		replacement.add(replacementToken);
	    }
	}
	// Add all tokens behind macro
	for (int i = tokenId + parameterReplacements.getTokensToSkip() + 1; i < tokenStream
		.size(); i++) {
	    replacement.add(tokenStream.get(i));
	}
	replaceAllMacros(replacement);
	tokenStream.clear();
	tokenStream.addAll(replacement);
	return true;
    }

    /**
     * Finds a parameter id for a given token. The text of the token is
     * interpreted as macro name.
     * 
     * @return <0 is returned if the token is not a parameter name.
     * 
     */
    private static int findParameterId(List<String> parameters, Token token) {
	int foundParameterId = -1;
	for (int parameterId = 0; parameterId < parameters.size(); parameterId++) {
	    if (token.getText().equals(parameters.get(parameterId))) {
		foundParameterId = parameterId;
	    }
	}
	return foundParameterId;
    }

}
