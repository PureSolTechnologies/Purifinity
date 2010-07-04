package com.puresol.coding.evaluator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

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
		}
	}

	private final List<ProjectEvaluatorFactory> projectEvaluators = new ArrayList<ProjectEvaluatorFactory>();
	private final List<FileEvaluatorFactory> fileEvaluators = new ArrayList<FileEvaluatorFactory>();
	private final List<CodeRangeEvaluatorFactory> codeRangeEvaluators = new ArrayList<CodeRangeEvaluatorFactory>();

	private Evaluators() {
	}

	public List<ProjectEvaluatorFactory> getProjectEvaluators() {
		return projectEvaluators;
	}

	public List<FileEvaluatorFactory> getFileEvaluators() {
		return fileEvaluators;
	}

	public List<CodeRangeEvaluatorFactory> getCodeRangeEvaluators() {
		return codeRangeEvaluators;
	}

	public void registerProjectEvaluator(ProjectEvaluatorFactory evaluator) {
		logger.info("Register evaluator '" + evaluator.getClass().getName()
				+ "'...");
		projectEvaluators.add(evaluator);
	}

	public void unregisterProjectEvaluator(ProjectEvaluatorFactory evaluator) {
		logger.info("Unregister evaluator '" + evaluator.getClass().getName()
				+ "'...");
		projectEvaluators.remove(evaluator);
	}

	public void registerFileEvaluator(FileEvaluatorFactory evaluator) {
		logger.info("Register evaluator '" + evaluator.getClass().getName()
				+ "'...");
		fileEvaluators.add(evaluator);
	}

	public void unregisterFileEvaluator(FileEvaluatorFactory evaluator) {
		logger.info("Unregister evaluator '" + evaluator.getClass().getName()
				+ "'...");
		fileEvaluators.remove(evaluator);
	}

	public void registerCodeRangeEvaluator(CodeRangeEvaluatorFactory evaluator) {
		logger.info("Register evaluator '" + evaluator.getClass().getName()
				+ "'...");
		codeRangeEvaluators.add(evaluator);
	}

	public void unregisterCodeRangeEvaluator(CodeRangeEvaluatorFactory evaluator) {
		logger.info("Unregister evaluator '" + evaluator.getClass().getName()
				+ "'...");
		codeRangeEvaluators.remove(evaluator);
	}

}
