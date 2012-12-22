/***************************************************************************
 *
 *   AnalyserFactory.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.ProgrammingLanguages;
import com.puresol.coding.analysis.api.FileAnalyzer;
import com.puresol.coding.analysis.api.ProgrammingLanguage;

/**
 * This factory creates an Analyser class for a given File in dependence for its
 * implementation language.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FileAnalysisFactory {

    private static final Logger logger = LoggerFactory
	    .getLogger(FileAnalysisFactory.class);

    private static FileAnalysisFactory instance = null;

    public static FileAnalysisFactory createFactory() {
	if (instance == null) {
	    createInstance();
	}
	return instance;
    }

    private static synchronized void createInstance() {
	if (instance == null) {
	    instance = new FileAnalysisFactory();
	}
    }

    private FileAnalysisFactory() {
	// needs to be private...
    }

    public FileAnalyzer create(File sourceDirectory, File file)
	    throws LanguageNotSupportedException, FileNotFoundException {
	logger.debug("Create analyser for file '" + file.getPath() + "'...");
	checkFile(new File(sourceDirectory, file.getPath()));
	return createAnalyser(sourceDirectory, file);
    }

    private void checkFile(File file) throws FileNotFoundException {
	if (!file.exists()) {
	    logger.warn("File '" + file.getPath() + "' is not existing!");
	    throw new FileNotFoundException("File '" + file.getPath()
		    + "' is not existing!");
	}
    }

    private FileAnalyzer createAnalyser(File sourceDirectory, File file)
	    throws LanguageNotSupportedException {
	for (ProgrammingLanguage language : ProgrammingLanguages.getAll()) {
	    FileAnalyzer analyser = checkAndCreate(language, sourceDirectory,
		    file);
	    if (analyser != null) {
		return analyser;
	    }
	}
	logger.debug("No analyser for file '" + file.getPath() + "' found!");
	throw new LanguageNotSupportedException(
		"No coding language found for file " + file.getPath());
    }

    private FileAnalyzer checkAndCreate(ProgrammingLanguage clazz,
	    File sourceDirectory, File file) {
	if (!clazz.isSuitable(file)) {
	    return null;
	}
	return clazz.createAnalyser(sourceDirectory, file);
    }

    public FileAnalyzer restore(File persistFile) {
	try {
	    for (ProgrammingLanguage language : ProgrammingLanguages.getAll()) {
		FileAnalyzer analyzer = language.restoreAnalyzer(persistFile);
		if (analyzer != null) {
		    return analyzer;
		}
	    }
	    return null;
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    return null;
	}
    }
}