package com.puresol.coding.evaluator;

abstract public class AbstractReportGenerator implements ReportGenerator {

	private final CodeEvaluator codeEvaluator;

	public AbstractReportGenerator(CodeEvaluator evaluator) {
		this.codeEvaluator = evaluator;
	}

	public CodeEvaluator getCodeEvaluator() {
		return codeEvaluator;
	}

}
