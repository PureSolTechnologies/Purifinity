package com.puresol.coding.evaluator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.puresol.coding.evaluator.duplication.CopyAndPasteScannerFactory;
import com.puresol.coding.evaluator.duplication.DuplicationScannerFactory;

/**
 * This is the manager class for all evaluator factories which provide evaluator
 * functionality to the code analysis system.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Evaluators {

	private static final Logger logger = Logger.getLogger(Evaluators.class);

	private static Evaluators instance = null;

	public static Evaluators getInstance() {
		if (instance == null) {
			logger.info("No Evaluators instance initialized...");
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			logger.info("Create Evaluators instance...");
			instance = new Evaluators();
			// TODO remove the next two lines and put them somewhere else in a
			// bundle or so...
			instance.registerEvaluator(new CopyAndPasteScannerFactory());
			instance.registerEvaluator(new DuplicationScannerFactory());
		}
	}

	private final List<EvaluatorFactory> evaluators = new ArrayList<EvaluatorFactory>();

	private Evaluators() {
	}

	public List<EvaluatorFactory> getEvaluators() {
		return evaluators;
	}

	public void registerEvaluator(EvaluatorFactory evaluator) {
		logger.info("Register evaluator '" + evaluator.getClass().getName()
				+ "'...");
		evaluators.add(evaluator);
		logger.info("Now we have " + evaluators.size()
				+ " evaluators available.");
	}

	public void unregisterEvaluator(EvaluatorFactory evaluator) {
		logger.info("Unregister evaluator '" + evaluator.getClass().getName()
				+ "'...");
		evaluators.remove(evaluator);
		logger.info("Now we have " + evaluators.size()
				+ " evaluators available.");
	}

}
