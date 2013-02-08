package com.puresol.coding.analysis.api;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.analysis.api.AnalyzerException;
import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.CodeAnalyzer;
import com.puresol.coding.analysis.api.LanguageNotSupportedException;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.uhura.source.CodeLocation;
import com.puresol.utils.HashId;

public class CodeAnalyzerImpl implements CodeAnalyzer {

    private static final Logger logger = LoggerFactory
	    .getLogger(CodeAnalyzerImpl.class);

    private final CodeLocation source;
    private CodeAnalyzer analyzer = null;
    private boolean analyzed = false;
    private long timeOfRun;

    public CodeAnalyzerImpl(CodeLocation source, HashId hashId)
	    throws AnalyzerException {
	super();
	this.source = source;
    }

    @Override
    public void analyze() throws AnalyzerException, IOException {
	try {
	    analyzed = false;
	    timeOfRun = System.currentTimeMillis();
	    analyzeFile();
	    analyzed = true;
	    timeOfRun = System.currentTimeMillis() - timeOfRun;
	} catch (LanguageNotSupportedException e) {
	    logger.debug("File '" + source.getHumanReadableLocationString()
		    + "' could not be analyzed due to contents in a "
		    + "non-supported language.");
	}
    }

    private void analyzeFile() throws IOException, AnalyzerException,
	    LanguageNotSupportedException {
	analyzer = FileAnalysisFactory.createFactory().create(source);
	analyzer.analyze();
    }

    public boolean isAnalyzed() {
	return analyzed;
    }

    public CodeAnalyzer getAnalyzer() {
	return analyzer;
    }

    @Override
    public CodeAnalysis getAnalysis() {
	return analyzer.getAnalysis();
    }

    @Override
    public ProgrammingLanguage getLanguage() {
	return analyzer.getLanguage();
    }

    @Override
    public boolean persist(File file) {
	return false;
    }

    @Override
    public CodeLocation getSource() {
	return source;
    }

}
