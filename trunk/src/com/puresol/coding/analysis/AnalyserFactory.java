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
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.puresol.exceptions.StrangeSituationException;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Files;
import com.puresol.utils.Instances;
import com.puresol.utils.MethodInvokationException;

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

	private static ArrayList<Class<? extends Analyser>> analysers = new ArrayList<Class<? extends Analyser>>();
	static {
		for (String analyser : CodeAnalysisProperties.getPropertyValue(
				"CodeAnalysis.Analysers").split(",")) {
			Class<? extends Analyser> clazz = getAnalyserForName(analyser);
			if (clazz != null) {
				analysers.add(clazz);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends Analyser> getAnalyserForName(String analyser) {
		try {
			return (Class<? extends Analyser>) Class.forName(analyser);
		} catch (ClassNotFoundException e) {
			logger.error("Class '" + analyser
					+ "' was not found, but set in CodeAnalysis.properties!");
		}
		return null;
	}

	public static ArrayList<Class<? extends Analyser>> getAnalysers() {
		return analysers;
	}

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
		if (!Files.addPaths(projectDirectory, file).exists()) {
			logger.warn("File '" + file.getPath() + "' is not existing!");
			throw new FileNotFoundException("File '" + file.getPath()
					+ "' is not existing!");
		}
	}

	private Analyser createAnalyser(File projectDirectory, File file)
			throws LanguageNotSupportedException {
		for (Class<? extends Analyser> analyserClass : analysers) {
			Analyser analyser = checkAndCreate(analyserClass, projectDirectory,
					file);
			if (analyser != null) {
				return analyser;
			}
		}
		logger.warn("No analyser for file '" + file.getPath() + "' found!");
		throw new LanguageNotSupportedException(
				"No coding language found for file " + file.getPath());
	}

	private Analyser checkAndCreate(Class<? extends Analyser> clazz,
			File projectDirectory, File file) {
		try {

			if (!Instances.runStaticMethod(clazz, "isSuitable", boolean.class,
					file)) {
				return null;
			}
			return Instances.createInstance(clazz, projectDirectory, file);
		} catch (MethodInvokationException e) {
			throw new StrangeSituationException(e);
		} catch (ClassInstantiationException e) {
			throw new StrangeSituationException(e);
		}
	}
}
