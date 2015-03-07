package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.util.List;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.evaluation.domain.EvaluationResults;

public interface Metrics extends EvaluationResults {

    /**
     * This method returns a {@link List} of {@link Parameter} which contains
     * the definitions of all parameters which are returned by
     * {@link #getValues()}.
     * 
     * @return A {@link List} of {@link Parameter} is returned containing the
     *         definitions.
     */
    public Set<MetricParameter<?>> getParameters();

}
