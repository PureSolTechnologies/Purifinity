package com.puresol.coding.evaluator;

import com.puresol.coding.analysis.ProjectAnalyzer;

public interface ProjectEvaluatorFactory extends EvaluatorFactory {

	public ProjectEvaluator create(ProjectAnalyzer projectAnalyzer);

	public Class<? extends ProjectEvaluator> getProjectEvaluatorClass();

}
