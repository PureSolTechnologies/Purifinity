package com.puresol.coding.evaluator;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the manager class for all evaluator factories which provide evaluator
 * functionality to the code analysis system.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
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

	public void registerEvaluator(EvaluatorFactory evaluator) {
		evaluators.add(evaluator);
	}

	public void unregisterEvaluator(EvaluatorFactory evaluator) {
		evaluators.remove(evaluator);
	}

}
