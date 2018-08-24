package com.puresoltechnologies.purifinity.evaluation.api.metrics;

import java.util.Set;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;

/**
 * This interface describes metric evaluators which are exclusively used to
 * measure and calculate metrics.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface MetricEvaluator extends Evaluator {

    /**
     * @return A {@link Set} of {@link MetricParameter} is returned which are
     *         calculated by this evaluator.
     */
    @Override
    public MetricParameter<?>[] getParameters();

}
