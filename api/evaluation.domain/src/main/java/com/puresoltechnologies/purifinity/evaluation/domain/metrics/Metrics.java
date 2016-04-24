package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.util.List;

import com.puresoltechnologies.commons.domain.Parameter;
import com.puresoltechnologies.purifinity.evaluation.domain.EvaluationResults;

public interface Metrics extends EvaluationResults {

    /**
     * This method returns a {@link List} of {@link Parameter} which contains
     * the definitions of all parameters calculated by the implementation.
     * 
     * @return An array of {@link Parameter} is returned containing the
     *         definitions.
     */
    public MetricParameter<?>[] getParameters();

}
