package com.puresol.coding.evaluator;

import com.puresol.coding.analysis.Analyzer;

public abstract class AbstractFileEvaluator extends AbstractEvaluator implements
		FileEvaluator {

	private static final long serialVersionUID = -5689175506822461035L;

	private final Analyzer analyzer;

	public AbstractFileEvaluator(Analyzer analyzer) {
		this.analyzer = analyzer;
	}

	@Override
	public final Analyzer getAnalyzer() {
		return analyzer;
	}

}
