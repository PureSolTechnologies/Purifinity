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

import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.CodeEvaluationSystem;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.QualityLevel;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;

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
		for (Class<? extends Metric> metric : CodeEvaluationSystem
				.getMetricClasses()) {
			try {
				metrics
						.put(metric, Instances
								.createInstance(metric, codeRange));
			} catch (ClassInstantiationException e) {
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
			if (CodeEvaluationSystem.isMetricEvaluate(metric)) {
				level = QualityLevel.getMinLevel(level, metrics.get(metric)
						.getQualityLevel());
			}
		}
		return level;
	}
}
