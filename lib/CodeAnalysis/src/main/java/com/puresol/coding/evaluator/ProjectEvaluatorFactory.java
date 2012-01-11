package com.puresol.coding.evaluator;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.config.Configuration;

public interface ProjectEvaluatorFactory extends EvaluatorFactory {

	public ProjectEvaluator create(ProjectAnalyzer projectAnalyzer,
			Configuration configuration);

	public Class<? extends ProjectEvaluator> getProjectEvaluatorClass();

}
