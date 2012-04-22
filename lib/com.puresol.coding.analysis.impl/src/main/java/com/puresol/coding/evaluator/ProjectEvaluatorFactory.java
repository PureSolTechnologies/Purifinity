package com.puresol.coding.evaluator;

import com.puresol.coding.analysis.AnalysisRunImpl;
import com.puresol.coding.evaluation.api.EvaluatorFactory;

public interface ProjectEvaluatorFactory extends EvaluatorFactory {

    public ProjectEvaluator create(AnalysisRunImpl projectAnalyzer);

}
