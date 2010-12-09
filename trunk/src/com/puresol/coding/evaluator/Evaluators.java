package com.puresol.coding.evaluator;

import java.util.ArrayList;
import java.util.List;

import javax.swingx.connect.ConnectionHandler;
import javax.swingx.connect.ConnectionManager;
import javax.swingx.connect.Signal;

import org.apache.log4j.Logger;

/**
 * This is the manager class for all evaluator factories which provide evaluator
 * functionality to the code analysis system.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Evaluators implements ConnectionHandler {

	private static final Logger logger = Logger.getLogger(Evaluators.class);

	private final ConnectionManager connectionManager = ConnectionManager
			.createFor(this);

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
		changedProjectEvaluator(evaluator);
	}

	public void unregisterProjectEvaluator(ProjectEvaluatorFactory evaluator) {
		logger.info("Unregister evaluator '" + evaluator.getClass().getName()
				+ "'...");
		projectEvaluators.remove(evaluator);
		changedProjectEvaluator(evaluator);
	}

	public void registerFileEvaluator(FileEvaluatorFactory evaluator) {
		logger.info("Register evaluator '" + evaluator.getClass().getName()
				+ "'...");
		fileEvaluators.add(evaluator);
		changedFileEvaluator(evaluator);
	}

	public void unregisterFileEvaluator(FileEvaluatorFactory evaluator) {
		logger.info("Unregister evaluator '" + evaluator.getClass().getName()
				+ "'...");
		fileEvaluators.remove(evaluator);
		changedFileEvaluator(evaluator);
	}

	public void registerCodeRangeEvaluator(CodeRangeEvaluatorFactory evaluator) {
		logger.info("Register evaluator '" + evaluator.getClass().getName()
				+ "'...");
		codeRangeEvaluators.add(evaluator);
		changedCodeRangeEvaluator(evaluator);
	}

	public void unregisterCodeRangeEvaluator(CodeRangeEvaluatorFactory evaluator) {
		logger.info("Unregister evaluator '" + evaluator.getClass().getName()
				+ "'...");
		codeRangeEvaluators.remove(evaluator);
		changedCodeRangeEvaluator(evaluator);
	}

	@Signal
	public void changedProjectEvaluator(
			ProjectEvaluatorFactory projectEvaluatorFactory) {
		connectionManager.emitSignal("changedProjectEvaluator",
				projectEvaluatorFactory);
		changedProjectEvaluator();
	}

	@Signal
	public void changedProjectEvaluator() {
		connectionManager.emitSignal("changedProjectEvaluator");
	}

	@Signal
	public void changedFileEvaluator(FileEvaluatorFactory fileEvaluatorFactory) {
		connectionManager.emitSignal("changedFileEvaluator",
				fileEvaluatorFactory);
		changedFileEvaluator();
	}

	@Signal
	public void changedFileEvaluator() {
		connectionManager.emitSignal("changedFileEvaluator");
	}

	@Signal
	public void changedCodeRangeEvaluator(
			CodeRangeEvaluatorFactory codeRangeEvaluatorFactory) {
		connectionManager.emitSignal("changedCodeRangeEvaluator",
				codeRangeEvaluatorFactory);
		changedCodeRangeEvaluator();
	}

	@Signal
	public void changedCodeRangeEvaluator() {
		connectionManager.emitSignal("changedCodeRangeEvaluator");
	}

	@Override
	public void connect(String signal, Object receiver, String slot,
			Class<?>... types) {
		connectionManager.connect(signal, receiver, slot, types);
	}

	@Override
	public void release(String signal, Object receiver, String slot,
			Class<?>... types) {
		connectionManager.release(signal, receiver, slot, types);
	}

	@Override
	public boolean isConnected(String signal, Object receiver, String slot,
			Class<?>... types) {
		return connectionManager.isConnected(signal, receiver, slot, types);
	}

}
