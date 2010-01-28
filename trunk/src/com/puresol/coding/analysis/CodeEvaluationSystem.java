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

public class CodeEvaluationSystem {

    private static final ArrayList<AvailableMetrics> metrics =
	    new ArrayList<AvailableMetrics>();
    static {
	for (AvailableMetrics metric : AvailableMetrics.class
		.getEnumConstants()) {
	    metrics.add(metric);
	}
    }

    private static final ArrayList<Class<? extends Metric>> metricClasses =
	    new ArrayList<Class<? extends Metric>>();
    static {
	for (AvailableMetrics metric : metrics) {
	    metricClasses.add(metric.getMetricClass());
	}
    }

    private static final Hashtable<AvailableMetrics, Boolean> evaluate =
	    new Hashtable<AvailableMetrics, Boolean>();

    public static ArrayList<AvailableMetrics> getMetrics() {
	return metrics;
    }

    public static ArrayList<Class<? extends Metric>> getMetricClasses() {
	return metricClasses;
    }

    public static void setEvaluate(AvailableMetrics metric,
	    boolean evaluate) {
	CodeEvaluationSystem.evaluate.put(metric, evaluate);
    }

    public static boolean isEvaluate(AvailableMetrics metric) {
	Boolean bool = evaluate.get(metric);
	if (bool == null) {
	    return false;
	}
	return bool;
    }
}
