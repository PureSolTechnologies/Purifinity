package com.puresol.coding.evaluation.api;

public interface EvaluationResultsStore {

    public EvaluatorStore<FileResults, DirectoryResults, ProjectResults> createEvaluatorStore(
	    Class<? extends Evaluator<? extends EvaluatorResults>> evaluator);

}
