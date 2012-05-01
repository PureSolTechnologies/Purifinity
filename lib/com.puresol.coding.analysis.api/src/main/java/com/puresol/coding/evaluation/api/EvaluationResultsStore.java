package com.puresol.coding.evaluation.api;

public interface EvaluationResultsStore {

    public EvaluatorStore createEvaluatorStore(
	    Class<? extends Evaluator<? extends EvaluatorResults>> evaluator);

}
