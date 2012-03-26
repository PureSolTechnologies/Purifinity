/***************************************************************************
 *
 *   JavaAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.lang.java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.AnalyzerException;
import com.puresol.coding.lang.java.grammar.JavaGrammar;
import com.puresol.coding.lang.java.grammar.parts.AnnotationTypeDeclaration;
import com.puresol.coding.lang.java.grammar.parts.ConstructorDeclaration;
import com.puresol.coding.lang.java.grammar.parts.EnumDeclaration;
import com.puresol.coding.lang.java.grammar.parts.MethodDeclaration;
import com.puresol.coding.lang.java.grammar.parts.NormalClassDeclaration;
import com.puresol.coding.lang.java.grammar.parts.NormalInterfaceDeclaration;
import com.puresol.trees.TreeException;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.LexerResult;
import com.puresol.uhura.lexer.SourceCode;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.utils.StopWatch;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JavaAnalyzer implements Analyzer {

    private static final long serialVersionUID = -3601131473616977648L;

    private static final Logger logger = LoggerFactory
	    .getLogger(JavaAnalyzer.class);

    private final File file;
    private final transient JavaGrammar grammar;
    private Date date = new Date();
    private double timeEffort = 0;
    private ParserTree parserTree = null;

    public JavaAnalyzer(File file) {
	super();
	this.file = file;
	grammar = JavaGrammar.getInstance();
    }

    @Override
    public void parse() throws AnalyzerException {
	try {
	    date = new Date();
	    StopWatch watch = new StopWatch();
	    watch.start();
	    Lexer lexer = grammar.getLexer();
	    LexerResult lexerResult = lexer.lex(SourceCode.read(file),
		    file.toString());
	    Parser parser = grammar.getParser();
	    parserTree = parser.parse(lexerResult);
	    watch.stop();
	    timeEffort = watch.getSeconds();
	} catch (ParserException e) {
	    logger.error(e.getMessage(), e);
	    throw new AnalyzerException(this);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    throw new AnalyzerException(this);
	} catch (LexerException e) {
	    logger.error(e.getMessage(), e);
	    throw new AnalyzerException(this);
	}
	return;
    }

    @Override
    public ProgrammingLanguage getLanguage() {
	return Java.getInstance();
    }

    @Override
    public Date getTimeStamp() {
	return date;
    }

    @Override
    public double getTimeEffort() {
	return timeEffort;
    }

    @Override
    public File getFile() {
	return file;
    }

    @Override
    public boolean persist(File file) {
	try {
	    persist(this, file);
	    return true;
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    return false;
	}
    }

    @Override
    public ParserTree getParserTree() {
	return parserTree;
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
		    if (NormalClassDeclaration.is(tree)) {
			result.add(new NormalClassDeclaration(tree)
				.getCodeRange());
		    } else if (EnumDeclaration.is(tree)) {
			result.add(new EnumDeclaration(tree).getCodeRange());
		    } else if (NormalInterfaceDeclaration.is(tree)) {
			result.add(new NormalInterfaceDeclaration(tree)
				.getCodeRange());
		    } else if (AnnotationTypeDeclaration.is(tree)) {
			result.add(new AnnotationTypeDeclaration(tree)
				.getCodeRange());
		    } else if (ConstructorDeclaration.is(tree)) {
			result.add(new ConstructorDeclaration(tree)
				.getCodeRange());
		    } else if (MethodDeclaration.is(tree)) {
			result.add(new MethodDeclaration(tree).getCodeRange());
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

    private <T> void persist(T object, File file) throws IOException {
	ObjectOutputStream objectOutputStream = new ObjectOutputStream(
		new FileOutputStream(file));
	try {
	    objectOutputStream.writeObject(object);
	} finally {
	    objectOutputStream.close();
	}
    }

}
