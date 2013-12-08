/***************************************************************************
 *
 *   AnalyserFactory.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.coding.analysis.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.parsers.impl.analyzer.Analyzer;
import com.puresoltechnologies.parsers.impl.source.CodeLocation;

/**
 * This factory creates an {@link Analyzer} class for a given File in dependence
 * for its implementation language.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CodeAnalyzerFactory {

    private static final Logger logger = LoggerFactory
	    .getLogger(CodeAnalyzerFactory.class);

    private static CodeAnalyzerFactory instance = null;

    public static CodeAnalyzerFactory createFactory() {
	if (instance == null) {
	    createInstance();
	}
	return instance;
    }

    private static synchronized void createInstance() {
	if (instance == null) {
	    instance = new CodeAnalyzerFactory();
	}
    }

    private CodeAnalyzerFactory() {
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
	ProgrammingLanguages programmingLanguages = ProgrammingLanguages
		.createInstance();
	try {
	    for (ProgrammingLanguageAnalyzer language : programmingLanguages
		    .getAll()) {
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
	} finally {
	    IOUtils.closeQuietly(programmingLanguages);
	}
    }

    private CodeAnalyzer checkAndCreate(ProgrammingLanguageAnalyzer clazz,
	    CodeLocation source) {
	if (!clazz.isSuitable(source)) {
	    return null;
	}
	return clazz.createAnalyser(source);
    }

    public CodeAnalyzer restore(File persistFile) {
	try {
	    ProgrammingLanguages programmingLanguages = ProgrammingLanguages
		    .createInstance();
	    try {
		for (ProgrammingLanguageAnalyzer language : programmingLanguages
			.getAll()) {
		    CodeAnalyzer analyzer = language
			    .restoreAnalyzer(persistFile);
		    if (analyzer != null) {
			return analyzer;
		    }
		}
		return null;
	    } finally {
		IOUtils.closeQuietly(programmingLanguages);
	    }
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    return null;
	}
    }
}
