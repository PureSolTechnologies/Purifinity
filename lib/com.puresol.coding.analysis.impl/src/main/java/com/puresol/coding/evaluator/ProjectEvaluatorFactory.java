package com.puresol.coding.evaluator;

import com.puresol.coding.analysis.AnalysisRunImpl;

public interface ProjectEvaluatorFactory extends EvaluatorFactory {

    public ProjectEvaluator create(AnalysisRunImpl projectAnalyzer);

    public Class<? extends ProjectEvaluator> getProjectEvaluatorClass();

}
