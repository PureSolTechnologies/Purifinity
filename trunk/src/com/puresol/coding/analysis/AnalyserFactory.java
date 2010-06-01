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

import org.apache.log4j.Logger;

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.ProgrammingLanguages;
import com.puresol.exceptions.StrangeSituationException;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.FileUtilities;

/**
 * This factory creates an Analyser class for a given File in dependence for its
 * implementation language.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalyserFactory {

	private static final Logger logger = Logger
			.getLogger(AnalyserFactory.class);

	private static AnalyserFactory instance = null;

	public static AnalyserFactory createFactory() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new AnalyserFactory();
		}
	}

	private AnalyserFactory() {
		// needs to be private...
	}

	public Analyser create(File projectDirectory, File file)
			throws LanguageNotSupportedException, FileNotFoundException {
		logger.info("Create analyser for file '" + file.getPath() + "'...");
		checkFile(projectDirectory, file);
		return createAnalyser(projectDirectory, file);
	}

	private void checkFile(File projectDirectory, File file)
			throws FileNotFoundException {
		if (!FileUtilities.addPaths(projectDirectory, file).exists()) {
			logger.warn("File '" + file.getPath() + "' is not existing!");
			throw new FileNotFoundException("File '" + file.getPath()
					+ "' is not existing!");
		}
	}

	private Analyser createAnalyser(File projectDirectory, File file)
			throws LanguageNotSupportedException {
		for (ProgrammingLanguage language : ProgrammingLanguages.getInstance()
				.getLanguages()) {
			Analyser analyser = checkAndCreate(language, projectDirectory, file);
			if (analyser != null) {
				return analyser;
			}
		}
		logger.warn("No analyser for file '" + file.getPath() + "' found!");
		throw new LanguageNotSupportedException(
				"No coding language found for file " + file.getPath());
	}

	private Analyser checkAndCreate(ProgrammingLanguage clazz,
			File projectDirectory, File file) {
		try {
			if (!clazz.isSuitable(FileUtilities.addPaths(projectDirectory, file))) {
				return null;
			}
			return clazz.createAnalyser(projectDirectory, file);
		} catch (ClassInstantiationException e) {
			throw new StrangeSituationException(e);
		}
	}
}
