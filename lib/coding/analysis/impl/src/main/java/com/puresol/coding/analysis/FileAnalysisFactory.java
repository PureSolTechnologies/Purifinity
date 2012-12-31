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
import com.puresol.coding.analysis.api.CodeAnalyzer;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.uhura.source.CodeLocation;

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

    public CodeAnalyzer create(CodeLocation source)
	    throws LanguageNotSupportedException, FileNotFoundException {
	logger.debug("Create analyser for file '"
		+ source.getHumanReadableLocationString() + "'...");
	return createAnalyser(source);
    }

    private CodeAnalyzer createAnalyser(CodeLocation source)
	    throws LanguageNotSupportedException {
	for (ProgrammingLanguage language : ProgrammingLanguages.getAll()) {
	    CodeAnalyzer analyser = checkAndCreate(language, source);
	    if (analyser != null) {
		return analyser;
	    }
	}
	logger.debug("No analyser for file '"
		+ source.getHumanReadableLocationString() + "' found!");
	throw new LanguageNotSupportedException(
		"No coding language found for file "
			+ source.getHumanReadableLocationString());
    }

    private CodeAnalyzer checkAndCreate(ProgrammingLanguage clazz, CodeLocation source) {
	if (!clazz.isSuitable(source)) {
	    return null;
	}
	return clazz.createAnalyser(source);
    }

    public CodeAnalyzer restore(File persistFile) {
	try {
	    for (ProgrammingLanguage language : ProgrammingLanguages.getAll()) {
		CodeAnalyzer analyzer = language.restoreAnalyzer(persistFile);
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
