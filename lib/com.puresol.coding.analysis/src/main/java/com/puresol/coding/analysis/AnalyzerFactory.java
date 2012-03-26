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

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.ProgrammingLanguages;

/**
 * This factory creates an Analyser class for a given File in dependence for its
 * implementation language.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalyzerFactory {

    private static final Logger logger = LoggerFactory
	    .getLogger(AnalyzerFactory.class);

    private static AnalyzerFactory instance = null;

    public static AnalyzerFactory createFactory() {
	if (instance == null) {
	    createInstance();
	}
	return instance;
    }

    private static synchronized void createInstance() {
	if (instance == null) {
	    instance = new AnalyzerFactory();
	}
    }

    private AnalyzerFactory() {
	// needs to be private...
    }

    public Analyzer create(File file) throws LanguageNotSupportedException,
	    FileNotFoundException {
	logger.debug("Create analyser for file '" + file.getPath() + "'...");
	checkFile(file);
	return createAnalyser(file);
    }

    private void checkFile(File file) throws FileNotFoundException {
	if (!file.exists()) {
	    logger.warn("File '" + file.getPath() + "' is not existing!");
	    throw new FileNotFoundException("File '" + file.getPath()
		    + "' is not existing!");
	}
    }

    private Analyzer createAnalyser(File file)
	    throws LanguageNotSupportedException {
	for (ProgrammingLanguage language : ProgrammingLanguages.getAll()) {
	    Analyzer analyser = checkAndCreate(language, file);
	    if (analyser != null) {
		return analyser;
	    }
	}
	logger.debug("No analyser for file '" + file.getPath() + "' found!");
	throw new LanguageNotSupportedException(
		"No coding language found for file " + file.getPath());
    }

    private Analyzer checkAndCreate(ProgrammingLanguage clazz, File file) {
	if (!clazz.isSuitable(file)) {
	    return null;
	}
	return clazz.createAnalyser(file);
    }

    public Analyzer restore(File persistFile) {
	try {
	    for (ProgrammingLanguage language : ProgrammingLanguages.getAll()) {
		Analyzer analyzer = language.restoreAnalyzer(persistFile);
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
