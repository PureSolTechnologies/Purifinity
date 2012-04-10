package com.puresol.coding.analysis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.analysis.api.AnalyzerException;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.FileAnalyzer;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.HashId;

public class FileAnalyzerImpl implements FileAnalyzer {

    private static final long serialVersionUID = -5962553384294545665L;

    private static final Logger logger = LoggerFactory
	    .getLogger(FileAnalyzerImpl.class);

    private final File sourceDirectory;
    private final File storageDirectory;
    private final File file;
    private final HashId hashId;
    private FileAnalyzer analyzer = null;
    private AnalyzedFile analyzedFile = null;
    private boolean analyzed = false;
    private boolean updated = false;
    private Date time;
    private long timeOfRun;

    public FileAnalyzerImpl(File sourceDirectory, File storageDirectory,
	    File file, HashId hashId) throws AnalyzerException {
	super();
	this.sourceDirectory = sourceDirectory;
	this.storageDirectory = storageDirectory;
	this.file = file;
	this.hashId = hashId;
    }

    @Override
    public void analyze() throws AnalyzerException, IOException {
	try {
	    analyzed = false;
	    updated = false;

	    time = new Date();
	    timeOfRun = System.currentTimeMillis();
	    analyzeIfRequired();
	    ProgrammingLanguage language = getAnalyzer().getLanguage();
	    analyzedFile = new AnalyzedFile(hashId, file, new Date(), 0,
		    language.getName(), language.getVersion());
	} catch (LanguageNotSupportedException e) {
	    analyzedFile = null;
	    logger.debug("File '" + file.getPath()
		    + "' could not be analyzed due to contents in a "
		    + "non-supported language.");
	}
    }

    private void analyzeIfRequired() throws AnalyzerException,
	    LanguageNotSupportedException, IOException {
	if (FileUtilities.isUpdateRequired(
		new File(sourceDirectory, file.getPath()),
		AnalyzedFileHelper.getPropertyFile(storageDirectory))) {
	    AnalyzedFileHelper.getAnalyzerFile(storageDirectory)
		    .getParentFile().mkdirs();
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
	timeOfRun = System.currentTimeMillis() - timeOfRun;
    }

    private void analyzeFile() throws IOException, AnalyzerException,
	    LanguageNotSupportedException {
	analyzer = FileAnalysisFactory.createFactory().create(
		new File(sourceDirectory, file.getPath()));
	analyzer.analyze();
	analyzer.persist(AnalyzedFileHelper.getAnalyzerFile(storageDirectory));
    }

    private void writeProperties() throws IOException {
	Properties props = new Properties();
	SimpleDateFormat dateFormat = new SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss");
	props.put("timestamp", dateFormat.format(analyzer.getTimeStamp()));
	props.put("language", analyzer.getLanguage().getName());
	props.store(
		new FileWriter(AnalyzedFileHelper
			.getPropertyFile(storageDirectory)),
		"PropertyFile for Analysis");
    }

    @Override
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

    @Override
    public Date getTimeStamp() throws IOException {
	return time;
    }

    @Override
    public long getTimeOfRun() throws IOException {
	return timeOfRun;
    }

    @Override
    public ProgrammingLanguage getLanguage() throws IOException {
	return analyzer.getLanguage();
    }

    @Override
    public ParserTree getParserTree() throws IOException {
	return analyzer.getParserTree();
    }

    @Override
    public List<CodeRange> getAnalyzableCodeRanges() {
	return analyzer.getAnalyzableCodeRanges();
    }

    @Override
    public boolean persist(File file) {
	return false;
    }

}
