/***************************************************************************
 *
 *   AbstractAnalysisReport.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis.reports;

import com.puresol.coding.evaluator.metric.CodeRangeMetrics;

abstract public class AbstractAnalysisReport implements AnalysisReport {

    private CodeRangeMetrics metrics = null;

    public AbstractAnalysisReport(CodeRangeMetrics metrics) {
	this.metrics = metrics;
    }

    public CodeRangeMetrics getMetrics() {
	return metrics;
    }

}
