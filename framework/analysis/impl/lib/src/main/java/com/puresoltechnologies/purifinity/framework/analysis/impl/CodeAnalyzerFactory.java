/***************************************************************************
 *
 *   AnalyserFactory.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.framework.analysis.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.impl.analyzer.Analyzer;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalyzer;
import com.puresoltechnologies.purifinity.analysis.api.LanguageNotSupportedException;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;

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

	public CodeAnalyzer create(SourceCodeLocation source)
			throws LanguageNotSupportedException, FileNotFoundException {
		logger.debug("Create analyser for file '"
				+ source.getHumanReadableLocationString() + "'...");
		return createAnalyser(source);
	}

	private CodeAnalyzer createAnalyser(SourceCodeLocation source)
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
			try {
				programmingLanguages.close();
			} catch (IOException e) {
				logger.warn("Could not close programming languages.", e);
			}
		}
	}

	private CodeAnalyzer checkAndCreate(ProgrammingLanguageAnalyzer clazz,
			SourceCodeLocation source) {
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
				programmingLanguages.close();
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
}
