package com.puresol.coding;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * This is the central programming languages specification manager. All
 * available programming languages should register itself here in this manager.
 * All tools and evaluators ask for supported languages and features within this
 * class.
 * 
 * This class is designed as singleton to avoid data inconsistencies during
 * different initializations.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProgrammingLanguages {

	private static final Logger logger = Logger
			.getLogger(ProgrammingLanguage.class);

	private static ProgrammingLanguages instance = null;

	public static ProgrammingLanguages getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new ProgrammingLanguages();
		}
	}

	private final List<ProgrammingLanguage> languages = new ArrayList<ProgrammingLanguage>();

	private ProgrammingLanguages() {
	}

	public List<ProgrammingLanguage> getLanguages() {
		return languages;
	}

	public void registerLanguage(ProgrammingLanguage language) {
		logger.info("Register programminglanguage '"
				+ language.getClass().getName() + "'...");
		languages.add(language);
	}

	public void unregisterLanguage(ProgrammingLanguage language) {
		logger.info("Unregister programminglanguage '"
				+ language.getClass().getName() + "'...");
		languages.remove(language);
	}
}
