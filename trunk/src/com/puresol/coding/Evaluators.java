package com.puresol.coding;

import java.util.ArrayList;
import java.util.List;

public class Evaluators {

	private final List<EvaluatorFactory> languages = new ArrayList<EvaluatorFactory>();

	private static Evaluators instance = null;

	public static Evaluators getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new Evaluators();
		}
	}

	private Evaluators() {
	}

	public List<EvaluatorFactory> getLanguages() {
		return languages;
	}

	public void registerLanguage(EvaluatorFactory language) {
		languages.add(language);
	}

	public void unregisterLanguage(EvaluatorFactory language) {
		languages.remove(language);
	}

}
