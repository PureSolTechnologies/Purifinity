package com.puresol.coding;

import java.io.File;

import org.apache.log4j.Logger;

import com.puresol.coding.java.JavaAnalyser;

public class AnalyserFactory {

	private static final Logger logger = Logger
			.getLogger(AnalyserFactory.class);

	public static Analyser createAnalyser(File projectDirectory, File file)
			throws LanguageNotSupportedException {
		logger.info("Create analyser for file '" + file.getPath() + "'...");
		if (JavaAnalyser.isSuitable(file)) {
			return new JavaAnalyser(projectDirectory, file);
		} else {
			logger.warn("No analyser for file '" + file.getPath() + "' found!");
			throw new LanguageNotSupportedException(
					"No coding language found for file " + file.getPath());
		}
	}
}
