/***************************************************************************
 *
 *   CodeRangeMetrics.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis.metrics;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.EvaluatorManager;
import com.puresol.coding.analysis.QualityLevel;

public class CodeRangeMetrics {

    private static final Logger logger = Logger
	    .getLogger(CodeRangeMetrics.class);

    private final Hashtable<Class<? extends Metric>, Metric> metrics = new Hashtable<Class<? extends Metric>, Metric>();
    private final CodeRange codeRange;

    public CodeRangeMetrics(CodeRange codeRange) {
	this.codeRange = codeRange;
	calculate();
    }

    private void calculate() {
	for (Class<? extends Metric> metric : EvaluatorManager
		.getMetricClasses()) {
	    try {
		Metric metricClass = metric.getConstructor(CodeRange.class)
			.newInstance(codeRange);
		metrics.put(metric, metricClass);
	    } catch (IllegalArgumentException e) {
		logger.warn(e.getMessage(), e);
	    } catch (SecurityException e) {
		logger.warn(e.getMessage(), e);
	    } catch (InstantiationException e) {
		logger.warn(e.getMessage(), e);
	    } catch (IllegalAccessException e) {
		logger.warn(e.getMessage(), e);
	    } catch (InvocationTargetException e) {
		logger.warn(e.getMessage(), e);
	    } catch (NoSuchMethodException e) {
		logger.warn(e.getMessage(), e);
	    }
	}
    }

    public CodeRange getCodeRange() {
	return codeRange;
    }

    public ArrayList<Class<? extends Metric>> getCalculatedMetrics() {
	return new ArrayList<Class<? extends Metric>>(metrics.keySet());
    }

    public Metric getMetric(Class<? extends Metric> metric) {
	return metrics.get(metric);
    }

    public QualityLevel getQualityLevel() {
	QualityLevel level = QualityLevel.HIGH;
	for (Class<? extends Metric> metric : metrics.keySet()) {
	    if (EvaluatorManager.isMetricEvaluate(metric)) {
		level = QualityLevel.getMinLevel(level, metrics.get(metric)
			.getQualityLevel());
	    }
	}
	return level;
    }
}
