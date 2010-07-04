package com.puresol.coding.evaluator;

import com.puresol.coding.analysis.Analyzer;

public interface FileEvaluatorFactory extends EvaluatorFactory {

	public FileEvaluator create(Analyzer analyzer);

}
