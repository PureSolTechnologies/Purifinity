package com.puresol.coding.evaluator;

import java.util.ArrayList;
import java.util.List;

import javax.swingx.connect.ConnectionHandler;
import javax.swingx.connect.ConnectionManager;
import javax.swingx.connect.Signal;

import org.apache.log4j.Logger;

import com.puresol.osgi.OSGIServiceManager;

/**
 * This is the manager class for all evaluator factories which provide evaluator
 * functionality to the code analysis system.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CodeRangeEvaluatorManager implements
		OSGIServiceManager<CodeRangeEvaluatorFactory>, ConnectionHandler {

	private static final Logger logger = Logger
			.getLogger(CodeRangeEvaluatorManager.class);

	private final ConnectionManager connectionManager = ConnectionManager
			.createFor(this);

	private static CodeRangeEvaluatorManager instance = null;

	public static CodeRangeEvaluatorManager getInstance() {
		if (instance == null) {
			logger.info("No Evaluators instance initialized...");
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			logger.info("Create Evaluators instance...");
			instance = new CodeRangeEvaluatorManager();
		}
	}

	private final List<CodeRangeEvaluatorFactory> codeRangeEvaluators = new ArrayList<CodeRangeEvaluatorFactory>();

	private CodeRangeEvaluatorManager() {
	}

	@Override
	public List<CodeRangeEvaluatorFactory> getAll() {
		return codeRangeEvaluators;
	}

	@Override
	public void register(CodeRangeEvaluatorFactory evaluator) {
		logger.info("Register evaluator '" + evaluator.getClass().getName()
				+ "'...");
		codeRangeEvaluators.add(evaluator);
		changedCodeRangeEvaluator(evaluator);
	}

	@Override
	public void unregister(CodeRangeEvaluatorFactory evaluator) {
		logger.info("Unregister evaluator '" + evaluator.getClass().getName()
				+ "'...");
		codeRangeEvaluators.remove(evaluator);
		changedCodeRangeEvaluator(evaluator);
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
