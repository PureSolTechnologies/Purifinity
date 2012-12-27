package com.puresol.coding.lang.cpp.internal;

import java.io.File;
import java.io.IOException;

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

    private final SourceCode code = new SourceCode();
    private final ParserTree tree;

    public TreeMacroProcessor(ParserTree tree) {
	super();
	this.tree = tree;
    }

    @Override
    public WalkingAction visit(ParserTree tree) {
	try {
	    Token token = tree.getToken();
	    if (token == null) {
		if ("IncludeMacro".equals(tree.getName())) {
		    ParserTree includeString = tree.getChild("IncludeFile");
		    String includeFile = includeString.getText();
		    /*
		     * We need to trim next due to we have looked for the
		     * production "IncludeFile" which may carry some white
		     * spaces.
		     */
		    includeFile = includeFile.trim();
		    logger.debug("Include file '" + includeFile + "',");
		    try {
			Source source = getIncludeSource(tree);
			include(source, code, includeFile);
		    } catch (PreprocessorException e) {
			logger.warn("Abort preprocessing of file!", e);
			return WalkingAction.ABORT;
		    }
		    return WalkingAction.LEAVE_BRANCH;
		}
	    } else {
		if ("SourceCodeLine".equals(token.getName())) {
		    TokenMetaData metaData = token.getMetaData();
		    SourceCodeLine sourceCodeLine = new SourceCodeLine(
			    metaData.getSource(), metaData.getLine(),
			    token.getText());
		    code.addSourceCodeLine(sourceCodeLine);
		} else if ("LineTerminator".equals(token.getName())) {
		    /*
		     * LineTerminators are used to separate macros. The macros
		     * are processed and the remaining line terminators must be
		     * neglected.
		     */
		} else {
		    /*
		     * We should not come here. A token is either a line of
		     * source code which is handled above, or we interpret the
		     * preprocessor statement into something else by node
		     * handling. But we should not run into a case where we need
		     * to process a token from a preprocessor statement
		     * singularly!
		     * 
		     * We can just abort now...
		     */
		    throw new RuntimeException("Should not happen!");
		    // return WalkingAction.ABORT;
		}

	    }
	    return WalkingAction.PROCEED;
	} catch (TreeException e) {
	    throw new RuntimeException(
		    "The grammar or the implementation might be wrong!", e);
	}
    }

    private Source getIncludeSource(ParserTree tree) throws TreeException {
	ParserTree includeFile = tree.getChild("IncludeFile");
	ParserTree node = includeFile.getChild("StringLiteral");
	if (node == null) {
	    node = includeFile.getChild("FileIncludeLiteral");
	}
	return node.getMetaData().getSource();
    }

    private void include(Source source, SourceCode code, String includeFile)
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

    public void process() {
	new TreeWalker<ParserTree>(tree).walk(this);
    }

    public SourceCode getCode() {
	return code;
    }

}
