/***************************************************************************
 *
 *   JavaAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.lang.c11;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.analysis.api.AnalyzedCode;
import com.puresol.coding.analysis.api.AnalyzerException;
import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.CodeAnalyzer;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.coding.lang.c11.grammar.C11Grammar;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.parser.packrat.PackratParser;
import com.puresol.uhura.source.CodeLocation;
import com.puresol.uhura.source.SourceCode;
import com.puresol.utils.StopWatch;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class C11Analyzer implements CodeAnalyzer {

    private static final Logger logger = LoggerFactory
	    .getLogger(C11Analyzer.class);

    private final CodeLocation sourceCodeLocation;
    private final transient C11Grammar grammar;
    private CodeAnalysis fileAnalysis;

    public C11Analyzer(CodeLocation sourceCodeLocation) {
	super();
	this.sourceCodeLocation = sourceCodeLocation;
	grammar = C11Grammar.getInstance();
    }

    @Override
    public void analyze() throws AnalyzerException {
	try {
	    fileAnalysis = null;
	    Date date = new Date();
	    StopWatch watch = new StopWatch();
	    SourceCode sourceCode = sourceCodeLocation.load();
	    watch.start();
	    PackratParser packratParser = new PackratParser(grammar);
	    ParserTree parserTree = packratParser.parse(sourceCode);
	    watch.stop();
	    long timeEffort = Math.round(watch.getSeconds() * 1000.0);
	    C11 c11 = C11.getInstance();
	    AnalyzedCode analyzedFile = new AnalyzedCode(
		    sourceCode.getHashId(), sourceCodeLocation, date,
		    timeEffort, c11.getName(), c11.getVersion());
	    fileAnalysis = new CodeAnalysis(date, timeEffort, c11.getName(),
		    c11.getVersion(), analyzedFile, parserTree,
		    this.getAnalyzableCodeRanges(parserTree));
	} catch (ParserException e) {
	    logger.error(e.getMessage(), e);
	    throw new AnalyzerException(this);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    throw new AnalyzerException(this);
	}
	return;
    }

    @Override
    public CodeLocation getSource() {
	return sourceCodeLocation;
    }

    @Override
    public CodeAnalysis getAnalysis() {
	return fileAnalysis;
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

    private List<CodeRange> getAnalyzableCodeRanges(ParserTree parserTree) {
	throw new IllegalStateException("No implemented, yet!");
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

    @Override
    public ProgrammingLanguage getLanguage() {
	return C11.getInstance();
    }

}
