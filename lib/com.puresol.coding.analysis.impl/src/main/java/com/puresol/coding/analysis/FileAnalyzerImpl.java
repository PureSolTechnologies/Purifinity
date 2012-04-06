package com.puresol.coding.analysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.analysis.api.AnalyzerException;
import com.puresol.coding.analysis.api.FileAnalyzer;
import com.puresol.utils.FileUtilities;

public class FileAnalyzerImpl {

    private static final Logger logger = LoggerFactory
	    .getLogger(FileAnalyzerImpl.class);

    private final File sourceDirectory;
    private final File targetDirectory;
    private final File file;
    private FileAnalyzer analyzer = null;
    private AnalyzedFile analyzedFile = null;
    private boolean analyzed = false;
    private boolean updated = false;

    public FileAnalyzerImpl(File sourceDirectory, File targetDirectory,
	    File file) throws AnalyzerException {
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
	    if (!analyzedFile.getSourceFile().isFile()) {
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
		AnalyzedFileHelper.getPropertyFile(analyzedFile))) {
	    AnalyzedFileHelper.getAnalyzerFile(analyzedFile).getParentFile()
		    .mkdirs();
	    analyzeFile();
	    analyzed = true;
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
	analyzer = FileAnalysisFactory.createFactory().create(
		analyzedFile.getSourceFile());
	analyzer.analyze();
	analyzer.persist(AnalyzedFileHelper.getAnalyzerFile(analyzedFile));
    }

    private void writeProperties() throws IOException {
	Properties props = new Properties();
	SimpleDateFormat dateFormat = new SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss");
	props.put("timestamp", dateFormat.format(analyzer.getTimeStamp()));
	props.put("language", analyzer.getLanguage().getName());
	props.store(
		new FileWriter(AnalyzedFileHelper.getPropertyFile(analyzedFile)),
		"PropertyFile for Analysis");
    }

    public AnalyzedFile getAnalyzedFile() {
	return analyzedFile;
    }

    public boolean isAnalyzed() {
	return analyzed;
    }

    public FileAnalyzer getAnalyzer() {
	return analyzer;
    }

    public boolean isUpdated() {
	return updated;
    }

}
