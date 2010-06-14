package com.puresol.coding;

import java.util.ArrayList;
import java.util.List;

public class Evaluators {

	private final List<EvaluatorFactory> evaluators = new ArrayList<EvaluatorFactory>();

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

	public List<EvaluatorFactory> getEvaluators() {
		return evaluators;
	}

	public void registerLanguage(EvaluatorFactory language) {
		evaluators.add(language);
	}

	public void unregisterLanguage(EvaluatorFactory language) {
		evaluators.remove(language);
	}

}
