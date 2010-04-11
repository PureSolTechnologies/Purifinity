package com.puresol.coding.evaluator;

abstract public class AbstractReportGenerator implements ReportGenerator {

	private final ProjectEvaluator codeEvaluator;

	public AbstractReportGenerator(ProjectEvaluator evaluator) {
		this.codeEvaluator = evaluator;
	}

	public ProjectEvaluator getCodeEvaluator() {
		return codeEvaluator;
	}

}
