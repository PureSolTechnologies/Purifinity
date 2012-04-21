package com.puresol.coding.evaluator;

import com.puresol.coding.evaluation.api.EvaluatorFactory;

/**
 * This interface is meant for all factories which create code range evaluators.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface CodeRangeEvaluatorFactory extends EvaluatorFactory {

    public Class<? extends CodeRangeEvaluator> getCodeRangeEvaluatorClass();

}
