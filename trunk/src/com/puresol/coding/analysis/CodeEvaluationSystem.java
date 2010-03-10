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

import com.puresol.coding.evaluator.AvailableEvaluators;
import com.puresol.coding.evaluator.CodeEvaluator;

public class CodeEvaluationSystem {

	private static final ArrayList<AvailableMetrics> metrics = new ArrayList<AvailableMetrics>();
	static {
		for (AvailableMetrics metric : AvailableMetrics.class
				.getEnumConstants()) {
			metrics.add(metric);
		}
	}

	private static final ArrayList<Class<? extends Metric>> metricClasses = new ArrayList<Class<? extends Metric>>();
	static {
		for (AvailableMetrics metric : metrics) {
			metricClasses.add(metric.getMetricClass());
		}
	}

	private static final Hashtable<AvailableMetrics, Boolean> evaluateMetrics = new Hashtable<AvailableMetrics, Boolean>();

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

	public static ArrayList<AvailableMetrics> getMetrics() {
		return metrics;
	}

	public static ArrayList<Class<? extends Metric>> getMetricClasses() {
		return metricClasses;
	}

	public static void setMetricEvaluate(AvailableMetrics metric,
			boolean evaluate) {
		CodeEvaluationSystem.evaluateMetrics.put(metric, evaluate);
	}

	public static boolean isMetricEvaluate(AvailableMetrics metric) {
		Boolean bool = evaluateMetrics.get(metric);
		if (bool == null) {
			return false;
		}
		return bool;
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
