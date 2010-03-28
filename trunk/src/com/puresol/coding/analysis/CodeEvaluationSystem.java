/***************************************************************************
 *
 *   CodeEvaluationSystem.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis;

import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.evaluator.AvailableEvaluators;
import com.puresol.coding.analysis.evaluator.CodeEvaluator;
import com.puresol.coding.analysis.metrics.Metric;
import com.puresol.exceptions.StrangeSituationException;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;

public class CodeEvaluationSystem {

	private static final Logger logger = Logger
			.getLogger(CodeEvaluationSystem.class);

	private static ArrayList<Class<? extends Metric>> metricClasses = new ArrayList<Class<? extends Metric>>();
	static {
		for (String analyser : CodeAnalysisProperties.getPropertyValue(
				"CodeAnalysis.Metrics").split(",")) {
			Class<? extends Metric> clazz = getMetricForName(analyser);
			if (clazz != null) {
				metricClasses.add(clazz);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends Metric> getMetricForName(String metric) {
		try {
			return (Class<? extends Metric>) Class.forName(metric);
		} catch (ClassNotFoundException e) {
			logger.error("Class '" + metric
					+ "' was not found, but set in CodeAnalysis.properties!");
		}
		return null;
	}

	private static final Hashtable<Class<? extends Metric>, Boolean> evaluateMetrics = new Hashtable<Class<? extends Metric>, Boolean>();

	private static final ArrayList<AvailableEvaluators> evaluators = new ArrayList<AvailableEvaluators>();
	static {
		for (AvailableEvaluators evaluator : AvailableEvaluators.class
				.getEnumConstants()) {
			evaluators.add(evaluator);
		}
	}

	private static final ArrayList<Class<? extends CodeEvaluator>> evaluatorClasses = new ArrayList<Class<? extends CodeEvaluator>>();
	static {
		for (AvailableEvaluators evaluator : evaluators) {
			evaluatorClasses.add(evaluator.getEvaluatorClass());
		}
	}

	private static final Hashtable<AvailableEvaluators, Boolean> evaluateEvaluator = new Hashtable<AvailableEvaluators, Boolean>();

	public static ArrayList<Class<? extends Metric>> getMetricClasses() {
		return metricClasses;
	}

	public static void setMetricEvaluate(Class<? extends Metric> metric,
			boolean evaluate) {
		CodeEvaluationSystem.evaluateMetrics.put(metric, evaluate);
	}

	public static boolean isMetricEvaluate(Class<? extends Metric> metric) {
		Boolean bool = evaluateMetrics.get(metric);
		if (bool == null) {
			return false;
		}
		return bool;
	}

	public Metric newMetricInstance(Class<? extends Metric> metric,
			CodeRange codeRange) {
		try {
			return Instances.createInstance(metric, codeRange);
		} catch (ClassInstantiationException e) {
			throw new StrangeSituationException(e);
		}
	}

	public static ArrayList<AvailableEvaluators> getEvaluators() {
		return evaluators;
	}

	public static ArrayList<Class<? extends CodeEvaluator>> getEvaluatorClasses() {
		return evaluatorClasses;
	}

	public static void setEvaluatorEvaluate(AvailableEvaluators evaluator,
			boolean evaluate) {
		CodeEvaluationSystem.evaluateEvaluator.put(evaluator, evaluate);
	}

	public static boolean isEvaluatorEvaluate(AvailableEvaluators evaluator) {
		Boolean bool = evaluateEvaluator.get(evaluator);
		if (bool == null) {
			return false;
		}
		return bool;
	}
}
