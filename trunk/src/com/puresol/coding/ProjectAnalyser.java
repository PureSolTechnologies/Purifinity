package com.puresol.coding;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.i18n4j.FileSearch;

import org.antlr.runtime.tree.RewriteEmptyStreamException;
import org.apache.log4j.Logger;

public class ProjectAnalyser {

    private static final Logger logger =
	    Logger.getLogger(ProjectAnalyser.class);

    private File directory;
    private String pattern;
    private Hashtable<File, Analyser> analysers =
	    new Hashtable<File, Analyser>();

    public ProjectAnalyser(File directory, String pattern) {
	this.directory = directory;
	this.pattern = pattern;
    }

    public void update() {
	analysers = new Hashtable<File, Analyser>();
	analyseFiles();
	calculateStatistics();
    }

    private void analyseFiles() {
	List<File> files = FileSearch.find(directory, pattern);
	for (File file : files) {
	    analyseFile(file);
	}
    }

    private void analyseFile(File file) {
	try {
	    if ((new File(directory.getPath() + "/" + file.getPath())
		    .isFile())
		    && (!file.getPath().contains("/."))) {
		analysers.put(file, AnalyserFactory.createAnalyser(
			directory, file));
	    }
	} catch (RewriteEmptyStreamException e) {
	    logger
		    .error("File '"
			    + file.getPath()
			    + "' could not be analysed due to coding error in source file!");
	} catch (LanguageNotSupportedException e) {
	    logger
		    .warn("File '"
			    + file.getPath()
			    + "' could not be analysed due to containing no supported language.");
	} catch (FileNotFoundException e) {
	    logger.warn("File '" + file.getPath() + "' is not existing!");
	}
    }

    private void calculateStatistics() {

    }

    public File getProjectDirectory() {
	return directory;
    }

    public Set<File> getFiles() {
	return analysers.keySet();
    }

    public Analyser getAnalyser(File file) {
	return analysers.get(file);
    }

    public QualityLevel getQualityLevel(File file) {
	QualityLevel level = QualityLevel.HIGH;
	for (CodeRange range : getCodeRanges(file)) {
	    QualityLevel qualityInReport =
		    getMetrics(file, range).getQualityLevel();
	    if (level.getLevel() > qualityInReport.getLevel()) {
		level = qualityInReport;
	    }
	}
	return level;
    }

    public ArrayList<CodeRange> getCodeRanges(File file) {
	if (analysers == null) {
	    return null;
	}
	if (file == null) {
	    return null;
	}
	if (!analysers.containsKey(file)) {
	    return null;
	}
	Analyser analyser = analysers.get(file);
	if (analyser == null) {
	    return null;
	}
	return analysers.get(file).getCodeRanges();
    }

    public CodeRangeMetrics getMetrics(File file, CodeRange codeRange) {
	Analyser analyser = analysers.get(file);
	if (analyser == null) {
	    return null;
	}
	return analyser.getMetrics(codeRange);
    }

    public Hashtable<Integer, String> getLexerTokens(File file) {
	return getAnalyser(file).getLexerTokens();
    }

    public Hashtable<Integer, String> getParserTokens(File file) {
	return getAnalyser(file).getParserTokens();
    }
}
