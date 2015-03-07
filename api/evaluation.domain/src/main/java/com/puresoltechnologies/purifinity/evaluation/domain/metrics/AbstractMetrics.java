package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.util.Date;

import com.puresoltechnologies.purifinity.evaluation.domain.AbstractEvaluatorResults;

public abstract class AbstractMetrics extends AbstractEvaluatorResults
	implements Metrics {

    private static final long serialVersionUID = -139741759862514010L;

    public AbstractMetrics(String evaluatorId, Date time) {
	super(evaluatorId, time);
    }

}
