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
public class ProjectEvaluatorManager implements
		OSGIServiceManager<ProjectEvaluatorFactory>, ConnectionHandler {

	private static final Logger logger = Logger
			.getLogger(ProjectEvaluatorManager.class);

	private final ConnectionManager connectionManager = ConnectionManager
			.createFor(this);

	private static ProjectEvaluatorManager instance = null;

	public static ProjectEvaluatorManager getInstance() {
		if (instance == null) {
			logger.info("No Evaluators instance initialized...");
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			logger.info("Create Evaluators instance...");
			instance = new ProjectEvaluatorManager();
		}
	}

	private final List<ProjectEvaluatorFactory> projectEvaluators = new ArrayList<ProjectEvaluatorFactory>();

	private ProjectEvaluatorManager() {
	}

	@Override
	public List<ProjectEvaluatorFactory> getAll() {
		return projectEvaluators;
	}

	@Override
	public void register(ProjectEvaluatorFactory evaluator) {
		logger.info("Register evaluator '" + evaluator.getClass().getName()
				+ "'...");
		projectEvaluators.add(evaluator);
		changedProjectEvaluator(evaluator);
	}

	@Override
	public void unregister(ProjectEvaluatorFactory evaluator) {
		logger.info("Unregister evaluator '" + evaluator.getClass().getName()
				+ "'...");
		projectEvaluators.remove(evaluator);
		changedProjectEvaluator(evaluator);
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
	public void changedFileEvaluator() {
		connectionManager.emitSignal("changedFileEvaluator");
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
