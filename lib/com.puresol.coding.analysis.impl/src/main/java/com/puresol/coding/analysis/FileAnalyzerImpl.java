package com.puresol.coding.analysis;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.analysis.api.AnalyzerException;
import com.puresol.coding.analysis.api.FileAnalysis;
import com.puresol.coding.analysis.api.FileAnalyzer;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.utils.HashId;

public class FileAnalyzerImpl implements FileAnalyzer {

    private static final Logger logger = LoggerFactory
	    .getLogger(FileAnalyzerImpl.class);

    private final File sourceDirectory;
    private final File file;
    private FileAnalyzer analyzer = null;
    private boolean analyzed = false;
    private long timeOfRun;

    public FileAnalyzerImpl(File sourceDirectory, File file, HashId hashId)
	    throws AnalyzerException {
	super();
	this.sourceDirectory = sourceDirectory;
	this.file = file;
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
	    logger.debug("File '" + file.getPath()
		    + "' could not be analyzed due to contents in a "
		    + "non-supported language.");
	}
    }

    private void analyzeFile() throws IOException, AnalyzerException,
	    LanguageNotSupportedException {
	analyzer = FileAnalysisFactory.createFactory().create(
		new File(sourceDirectory, file.getPath()));
	analyzer.analyze();
    }

    public boolean isAnalyzed() {
	return analyzed;
    }

    public FileAnalyzer getAnalyzer() {
	return analyzer;
    }

    @Override
    public FileAnalysis getAnalysis() {
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
    public File getFile() {
	return file;
    }

}
