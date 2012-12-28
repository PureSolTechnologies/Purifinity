package com.puresol.coding.lang.cpp.internal;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.lang.cpp.CPreprocessor;
import com.puresol.coding.lang.cpp.IncludeDirectories;
import com.puresol.trees.TreeException;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenMetaData;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.preprocessor.PreprocessorException;
import com.puresol.uhura.source.Source;
import com.puresol.uhura.source.SourceCode;
import com.puresol.uhura.source.SourceCodeLine;

public class TreeMacroProcessor implements TreeVisitor<ParserTree> {

    private static final Logger logger = LoggerFactory
	    .getLogger(TreeMacroProcessor.class);

    private static final IncludeDirectories includeDirectories = IncludeDirectories
	    .getInstance();

    private final DefinedMacros definedMacros;
    private final SourceCode code = new SourceCode();
    private final ParserTree tree;

    public TreeMacroProcessor(ParserTree tree, DefinedMacros definedMacros) {
	super();
	this.tree = tree;
	this.definedMacros = definedMacros;
    }

    public void process() {
	new TreeWalker<ParserTree>(tree).walk(this);
    }

    public SourceCode getCode() {
	return code;
    }

    @Override
    public WalkingAction visit(ParserTree tree) {
	try {
	    Token token = tree.getToken();
	    if (token == null) {
		WalkingAction nextAction = processProduction(tree);
		return nextAction;
	    } else {
		processToken(token);
	    }
	    return WalkingAction.PROCEED;
	} catch (TreeException e) {
	    throw new RuntimeException(
		    "The grammar or the implementation might be wrong!", e);
	}
    }

    private WalkingAction processProduction(ParserTree tree)
	    throws TreeException {
	try {
	    if ("IncludeMacro".equals(tree.getName())) {
		return processInclude(tree);
	    } else if ("DefineObjectLikeMacro".equals(tree.getName())) {
		return processDefineObjectLikeMacro(tree);
	    } else if ("DefineFunctionLikeMacro".equals(tree.getName())) {
		return processDefineFunctionLikeMacro(tree);
	    } else {
		return WalkingAction.PROCEED;
	    }
	} catch (PreprocessorException e) {
	    logger.warn("Abort preprocessing of file!", e);
	    return WalkingAction.ABORT;
	}
    }

    private WalkingAction processInclude(ParserTree tree) throws TreeException,
	    PreprocessorException {
	ParserTree includeString = tree.getChild("IncludeFile");
	String includeFile = includeString.getText();
	/*
	 * We need to trim next due to we have looked for the production
	 * "IncludeFile" which may carry some white spaces.
	 */
	includeFile = includeFile.trim();
	logger.debug("Include file '" + includeFile + "',");
	Source source = getIncludeSource(tree);
	include(source, includeFile);
	return WalkingAction.LEAVE_BRANCH;
    }

    private WalkingAction processDefineObjectLikeMacro(ParserTree tree)
	    throws TreeException {
	String identifier = tree.getChild("Identifier").getText();
	ParserTree replacementList = tree.getChild("ReplacementList");
	if (replacementList != null) {
	    final StringBuffer replacement = new StringBuffer();
	    TreeVisitor<ParserTree> visitor = new TreeVisitor<ParserTree>() {
		@Override
		public WalkingAction visit(ParserTree tree) {
		    Token token = tree.getToken();
		    if (token != null) {
			if ((!"WhiteSpace".equals(token.getName()))
				|| (replacement.length() > 0)) {
			    replacement.append(token.getText());
			}
		    }
		    return WalkingAction.PROCEED;
		}
	    };
	    new TreeWalker<ParserTree>(replacementList).walk(visitor);
	    definedMacros.define(identifier, replacement.toString());
	} else {
	    definedMacros.define(identifier);
	}
	return WalkingAction.LEAVE_BRANCH;
    }

    private WalkingAction processDefineFunctionLikeMacro(ParserTree tree) {
	// TODO Auto-generated method stub
	return WalkingAction.LEAVE_BRANCH;
    }

    /**
     * This method deals with the tokens in the tree. We should only have
     * SourceCodeLine and LineTerminator tokens. Everything else should be part
     * of macros. Otherwise, we have an implementation issue.
     * 
     * @param token
     *            is the token to be processed.
     */
    private void processToken(Token token) {
	if ("SourceCodeLine".equals(token.getName())) {
	    /*
	     * We found an ordinary SourceCodeLine which is just added to the
	     * result.
	     */
	    TokenMetaData metaData = token.getMetaData();
	    String text = token.getText();
	    text = performMacroReplacement(text);
	    SourceCodeLine sourceCodeLine = new SourceCodeLine(
		    metaData.getSource(), metaData.getLine(), text);
	    code.addSourceCodeLine(sourceCodeLine);
	} else if ("LineTerminator".equals(token.getName())) {
	    /*
	     * LineTerminators are used to separate macros. The macros are
	     * processed and the remaining line terminators must be neglected.
	     */
	} else {
	    /*
	     * We should not come here. A token is either a line of source code
	     * which is handled above, or we interpret the preprocessor
	     * statement into something else by node handling. But we should not
	     * run into a case where we need to process a token from a
	     * preprocessor statement singularly!
	     * 
	     * We can just abort now...
	     */
	    throw new RuntimeException("Should not happen!");
	}
    }

    private String performMacroReplacement(String text) {
	for (Macro macro : definedMacros.getMacros()) {
	    Pattern pattern1 = Pattern.compile("^" + macro.getName() + "$");
	    text = processReplacement(pattern1, text, macro);
	    Pattern pattern2 = Pattern.compile("^" + macro.getName() + "\\s");
	    text = processReplacement(pattern2, text, macro);
	    Pattern pattern3 = Pattern.compile("\\s" + macro.getName() + "\\s");
	    text = processReplacement(pattern3, text, macro);
	    Pattern pattern4 = Pattern.compile("\\s" + macro.getName() + "$");
	    text = processReplacement(pattern4, text, macro);
	}
	return text;
    }

    private String processReplacement(Pattern pattern, String text, Macro macro) {
	Matcher matcher = pattern.matcher(text);
	while (matcher.find()) {
	    text = text.replace(matcher.group(), macro.getReplacement());
	}
	return text;
    }

    /**
     * Determines the source of the include statement. This is used later on to
     * calculate the position for later relative include file positions.
     * 
     * @param tree
     * @return
     * @throws TreeException
     */
    private Source getIncludeSource(ParserTree tree) throws TreeException {
	ParserTree includeFile = tree.getChild("IncludeFile");
	ParserTree node = includeFile.getChild("StringLiteral");
	if (node == null) {
	    node = includeFile.getChild("FileIncludeLiteral");
	}
	return node.getMetaData().getSource();
    }

    /**
     * This method performs the actual include.
     * 
     * @param source
     * @param includeFile
     * @throws PreprocessorException
     */
    private void include(Source source, String includeFile)
	    throws PreprocessorException {
	boolean includeFileDirectory = false;
	if (includeFile.startsWith("\"") && includeFile.endsWith("\"")) {
	    includeFileDirectory = true;
	}
	includeFile = includeFile.substring(1, includeFile.length() - 1);
	if (includeFileDirectory) {
	    Source includeSource = source.newRelativeSource(includeFile);
	    try {
		// read to be included source...
		SourceCode sourceCode = includeSource.load();
		// we need to process this source, too, before we can
		// include it...
		SourceCode processedSourceCode = new CPreprocessor()
			.process(sourceCode);
		// we actually do the including...
		code.addSourceCode(processedSourceCode);
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
