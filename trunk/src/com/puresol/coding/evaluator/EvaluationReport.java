package com.puresol.coding.evaluator;

import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;

public class EvaluationReport {

	private final CodeEvaluator evaluator;

	public EvaluationReport(CodeEvaluator evaluator) {
		this.evaluator = evaluator;
	}
	
	public StyledDocument getReport() {
		return new DefaultStyledDocument();
	}

}
