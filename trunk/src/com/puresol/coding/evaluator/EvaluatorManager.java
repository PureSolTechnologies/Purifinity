package com.puresol.coding.evaluator;

import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.puresol.coding.evaluator.metric.Metric;

/**
 * This singleton class is the central manager for all metrics and evaluators
 * which can be used for code evaluation.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EvaluatorManager {

	private static final Logger logger = Logger
			.getLogger(EvaluatorManager.class);

	private static EvaluatorManager instance = null;

	public static EvaluatorManager getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	public static synchronized void createInstance() {
		if (instance == null) {
			instance = new EvaluatorManager();
		}
	}

	private final ArrayList<Class<? extends Metric>> metricClasses = new ArrayList<Class<? extends Metric>>();
	private final Hashtable<Class<? extends Metric>, Boolean> evaluateMetrics = new Hashtable<Class<? extends Metric>, Boolean>();
	private final ArrayList<Class<? extends Evaluator>> evaluatorClasses = new ArrayList<Class<? extends Evaluator>>();
	private final Hashtable<Class<? extends Evaluator>, Boolean> evaluateEvaluator = new Hashtable<Class<? extends Evaluator>, Boolean>();
	private final Hashtable<Class<?>, String> names = new Hashtable<Class<?>, String>();

	private EvaluatorManager() {
		initStatics();
	}

	private void initStatics() {
		initMetrics();
		initEvaluators();
	}

	private void initMetrics() {
		for (String analyser : CodeEvaluationProperties.getPropertyValue(
				"CodeAnalysis.Metrics").split(",")) {
			Class<? extends Metric> clazz = getMetricForName(analyser);
			if (clazz != null) {
				metricClasses.add(clazz);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Class<? extends Metric> getMetricForName(String metric) {
		try {
			return (Class<? extends Metric>) Class.forName(metric);
		} catch (ClassNotFoundException e) {
			logger.error("Class '" + metric
					+ "' was not found, but set in CodeAnalysis.properties!");
		}
		return null;
	}

	private void initEvaluators() {
		for (String evaluator : CodeEvaluationProperties.getPropertyValue(
				"CodeAnalysis.Evaluators").split(",")) {
			Class<? extends Evaluator> clazz = getEvaluatorForName(evaluator);
			if (clazz != null) {
				evaluatorClasses.add(clazz);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Class<? extends Evaluator> getEvaluatorForName(String evaluator) {
		try {
			return (Class<? extends Evaluator>) Class.forName(evaluator);
		} catch (ClassNotFoundException e) {
			logger.error("Class '" + evaluator
					+ "' was not found, but set in CodeAnalysis.properties!");
		}
		return null;
	}

	public ArrayList<Class<? extends Metric>> getMetricClasses() {
		return metricClasses;
	}

	public void setMetricEvaluate(Class<? extends Metric> metric,
			boolean evaluate) {
		evaluateMetrics.put(metric, evaluate);
	}

	public boolean isMetricEvaluate(Class<? extends Metric> metric) {
		Boolean bool = evaluateMetrics.get(metric);
		if (bool == null) {
			return false;
		}
		return bool;
	}

	public ArrayList<Class<? extends Evaluator>> getEvaluatorClasses() {
		return evaluatorClasses;
	}

	public void setEvaluatorEvaluate(Class<? extends Evaluator> evaluator,
			boolean evaluate) {
		evaluateEvaluator.put(evaluator, evaluate);
	}

	public boolean isEvaluatorEvaluate(Class<? extends Evaluator> evaluator) {
		Boolean bool = evaluateEvaluator.get(evaluator);
		if (bool == null) {
			return false;
		}
		return bool;
	}

	public void setName(Class<?> clazz, String name) {
		logger.info("Register class '" + clazz.getName() + "' with name '"
				+ name + "'");
		names.put(clazz, name);
	}

	public String getName(Class<?> clazz) {
		return names.get(clazz);
	}
}
