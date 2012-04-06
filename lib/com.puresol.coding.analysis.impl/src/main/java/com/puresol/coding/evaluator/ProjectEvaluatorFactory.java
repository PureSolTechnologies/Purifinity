package com.puresol.coding.evaluator;

import com.puresol.coding.analysis.FileSystemAnalysisRun;

public interface ProjectEvaluatorFactory extends EvaluatorFactory {

    public ProjectEvaluator create(FileSystemAnalysisRun projectAnalyzer);

    public Class<? extends ProjectEvaluator> getProjectEvaluatorClass();

}
