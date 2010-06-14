/***************************************************************************
 *
 *   AbstractAnalysisReport.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.metrics.report;

import com.puresol.coding.evaluator.metric.CodeRangeMetrics;
import com.puresol.coding.evaluator.metric.report.AnalysisReport;

abstract public class AbstractMetricsReport implements AnalysisReport {

    private CodeRangeMetrics metrics = null;

    public AbstractMetricsReport(CodeRangeMetrics metrics) {
	this.metrics = metrics;
    }

    public CodeRangeMetrics getMetrics() {
	return metrics;
    }

}
