/***************************************************************************
 *
 *   FortranAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.lang.fortran;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.analysis.api.AnalyzerException;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.analysis.api.FileAnalyzer;
import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
import com.puresol.trees.TreeException;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.LexerResult;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.HashAlgorithm;
import com.puresol.utils.HashId;
import com.puresol.utils.StopWatch;

/**
 * This is the Fortran analyzer to scan and parse source files in Fortran source
 * code.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FortranAnalyzer implements FileAnalyzer {

    private static final long serialVersionUID = 2265150343844799735L;

    private static final Logger logger = LoggerFactory
	    .getLogger(FortranAnalyzer.class);

    private final File file;
    private final transient FortranGrammar grammar;
    private final Date date = new Date();
    private ParserTree parserTree = null;
    private long timeEffort = 0;
    private HashId hashId;

    public FortranAnalyzer(File file) {
	super();
	this.file = file;
	grammar = FortranGrammar.getInstance();
    }

    @Override
    public void analyze() throws AnalyzerException {
	try {
	    StopWatch watch = new StopWatch();
	    watch.start();
	    hashId = FileUtilities.createHashId(file, HashAlgorithm.SHA256);
	    LexerResult lexerResult = preConditioningAndLexing();
	    Parser parser = grammar.getParser();
	    parserTree = parser.parse(lexerResult);
	    watch.stop();
	    timeEffort = Math.round(watch.getSeconds() * 1000.0);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    throw new AnalyzerException(this);
	} catch (ParserException e) {
	    logger.error(e.getMessage(), e);
	    throw new AnalyzerException(this);
	}
    }

    private LexerResult preConditioningAndLexing() throws AnalyzerException {
	try {
	    FortranPreConditioner preconditioner = new FortranPreConditioner(
		    file);
	    TokenStream tokenStream = preconditioner.scan(grammar.getLexer());
	    return new LexerResult(tokenStream);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    throw new AnalyzerException(this);
	} catch (LexerException e) {
	    logger.error(e.getMessage(), e);
	    throw new AnalyzerException(this);
	}
    }

    @Override
    public Fortran getLanguage() {
	return Fortran.getInstance();
    }

    @Override
    public ParserTree getParserTree() {
	return parserTree;
    }

    @Override
    public AnalyzedFile getAnalyzedFile() {
	Fortran fortran = Fortran.getInstance();
	return new AnalyzedFile(hashId, file, date, timeEffort,
		fortran.getName(), fortran.getVersion());
    }

    @Override
    public Date getTimeStamp() {
	return date;
    }

    @Override
    public long getTimeOfRun() {
	return timeEffort;
    }

    @Override
    public boolean persist(File file) {
	try {
	    ObjectOutputStream objectOutputStream = new ObjectOutputStream(
		    new FileOutputStream(file));
	    try {
		objectOutputStream.writeObject(this);
	    } finally {
		objectOutputStream.close();
	    }
	    return true;
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    return false;
	}
    }

    @Override
    public List<CodeRange> getAnalyzableCodeRanges() {
	final List<CodeRange> result = new ArrayList<CodeRange>();
	result.add(new CodeRange("", CodeRangeType.FILE, parserTree));
	TreeWalker<ParserTree> walker = new TreeWalker<ParserTree>(parserTree);
	walker.walk(new TreeVisitor<ParserTree>() {

	    @Override
	    public WalkingAction visit(ParserTree tree) {
		try {
		    if ("main-program".equals(tree.getName())) {
			String name = tree.getChild("program-stmt")
				.getChildren("NAME_LITERAL").get(1).getText();
			result.add(new CodeRange(name, CodeRangeType.PROGRAM,
				tree));
		    } else if ("function-subprogram".equals(tree.getName())) {
			String name = tree.getChild("function-stmt")
				.getChildren("NAME_LITERAL").get(1).getText();
			result.add(new CodeRange(name, CodeRangeType.FUNCTION,
				tree));
		    } else if ("subroutine-subprogram".equals(tree.getName())) {
			String name = tree.getChild("subroutine-stmt")
				.getChildren("NAME_LITERAL").get(1).getText();
			result.add(new CodeRange(name,
				CodeRangeType.SUBROUTINE, tree));
		    } else if ("module".equals(tree.getName())) {
			String name = tree.getChild("module-stmt")
				.getChildren("NAME_LITERAL").get(1).getText();
			result.add(new CodeRange(name, CodeRangeType.MODULE,
				tree));
		    } else if ("submodule".equals(tree.getName())) {
			String name = tree.getChild("submodule-stmt")
				.getChildren("NAME_LITERAL").get(1).getText();
			result.add(new CodeRange(name, CodeRangeType.MODULE,
				tree));
		    }
		    return WalkingAction.PROCEED;
		} catch (TreeException e) {
		    logger.error(e.getMessage(), e);
		    return WalkingAction.ABORT;
		}
	    }

	});
	return result;
    }

}
