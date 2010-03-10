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

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.AvailableMetrics;
import com.puresol.coding.analysis.CodeEvaluationSystem;
import com.puresol.coding.analysis.QualityLevel;

public class CodeRangeMetrics {

    private final Hashtable<AvailableMetrics, Metric> metrics =
	    new Hashtable<AvailableMetrics, Metric>();
    private final CodeRange codeRange;

    public CodeRangeMetrics(CodeRange codeRange) {
	this.codeRange = codeRange;
	calculate();
    }

    private void calculate() {
	for (AvailableMetrics metric : CodeEvaluationSystem.getMetrics()) {
	    metrics.put(metric, metric.newInstance(codeRange));
	}
    }

    public CodeRange getCodeRange() {
	return codeRange;
    }

    public ArrayList<AvailableMetrics> getCalculatedMetrics() {
	return new ArrayList<AvailableMetrics>(metrics.keySet());
    }

    public Metric getMetric(AvailableMetrics metric) {
	return metrics.get(metric);
    }

    public QualityLevel getQualityLevel() {
	QualityLevel level = QualityLevel.HIGH;
	for (AvailableMetrics metric : metrics.keySet()) {
	    if (CodeEvaluationSystem.isMetricEvaluate(metric)) {
		level =
			QualityLevel.getMinLevel(level, metrics
				.get(metric).getQualityLevel());
	    }
	}
	return level;
    }
}
