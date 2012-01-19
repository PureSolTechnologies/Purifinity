package com.puresol.coding.analysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.utils.FileUtilities;

public class FileAnalyzer {

    private static final Logger logger = LoggerFactory
	    .getLogger(FileAnalyzer.class);

    private final File sourceDirectory;
    private final File targetDirectory;
    private final File file;
    private Analyzer analyzer = null;
    private AnalyzedFile analyzedFile = null;
    private boolean analyzed = false;
    private boolean updated = false;

    public FileAnalyzer(File sourceDirectory, File targetDirectory, File file)
	    throws AnalyzerException {
	super();
	this.sourceDirectory = sourceDirectory;
	this.targetDirectory = targetDirectory;
	this.file = file;
    }

    public void analyze() throws AnalyzerException, IOException {
	try {
	    analyzed = false;
	    updated = false;

	    analyzedFile = new AnalyzedFile(sourceDirectory, targetDirectory,
		    file);
	    if (analyzedFile.getFile().getPath().contains("/.")
		    || (!analyzedFile.getSourceFile().isFile())) {
		return;
	    }
	    analyzeIfRequired();
	} catch (LanguageNotSupportedException e) {
	    analyzedFile = null;
	    logger.debug("File '" + file.getPath()
		    + "' could not be analyzed due to contents in a "
		    + "non-supported language.");
	}
    }

    private void analyzeIfRequired() throws AnalyzerException,
	    LanguageNotSupportedException, IOException {
	if (FileUtilities.isUpdateRequired(analyzedFile.getSourceFile(),
		analyzedFile.getPropertyFile())) {
	    analyzeFile();
	    analyzed = true;
	    writeParserTree();
	    writeProperties();
	    updated = true;
	} else {
	    /*
	     * File is present and therefore, it is analyzed.
	     */
	    analyzed = true;
	}
    }

    private void analyzeFile() throws FileNotFoundException, AnalyzerException,
	    LanguageNotSupportedException {
	analyzer = AnalyzerFactory.createFactory().create(
		analyzedFile.getSourceFile());
	analyzer.parse();
	analyzer.persist(analyzedFile.getAnalyzerFile());
    }

    private void writeParserTree() throws IOException {
	// TODO think about requirement here. Is it needed?
	// Persistence.persist(analyzer.getParserTree(),
	// analyzedFile.getParserTreeFile());
    }

    private void writeProperties() throws IOException {
	Properties props = new Properties();
	SimpleDateFormat dateFormat = new SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss");
	props.put("timestamp", dateFormat.format(analyzer.getTimeStamp()));
	props.put("language", analyzer.getLanguage().getName());
	props.store(new FileWriter(analyzedFile.getPropertyFile()),
		"PropertyFile for Analysis");
    }

    public AnalyzedFile getAnalyzedFile() {
	return analyzedFile;
    }

    public boolean isAnalyzed() {
	return analyzed;
    }

    public Analyzer getAnalyzer() {
	return analyzer;
    }

    public boolean isUpdated() {
	return updated;
    }

}
