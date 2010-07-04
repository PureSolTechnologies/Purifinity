package com.puresol.coding.evaluator;

import com.puresol.coding.analysis.ProjectAnalyzer;

public abstract class AbstractProjectEvaluator extends AbstractEvaluator
		implements ProjectEvaluator {

	private static final long serialVersionUID = -5689175506822461035L;

	private final ProjectAnalyzer analyzer;

	public AbstractProjectEvaluator(ProjectAnalyzer analyser) {
		this.analyzer = analyser;
	}

	@Override
	public final ProjectAnalyzer getProjectAnalyser() {
		return analyzer;
	}

}
