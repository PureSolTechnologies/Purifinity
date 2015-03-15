package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.util.Date;

import com.puresoltechnologies.purifinity.evaluation.domain.AbstractEvaluatorResults;
import com.puresoltechnologies.versioning.Version;

public abstract class AbstractMetrics extends AbstractEvaluatorResults
	implements Metrics {

    private static final long serialVersionUID = -139741759862514010L;

    public AbstractMetrics(String evaluatorId, Version evaluatorVersion,
	    Date time) {
	super(evaluatorId, evaluatorVersion, time);
    }

}
