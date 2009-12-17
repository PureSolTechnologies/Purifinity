package com.puresol.coding;

abstract public class AbstractAnalysisReport implements AnalysisReport {

	private CodeRangeMetrics metrics = null;

	public AbstractAnalysisReport(CodeRangeMetrics metrics) {
		this.metrics = metrics;
	}

	public CodeRangeMetrics getMetrics() {
		return metrics;
	}
}
