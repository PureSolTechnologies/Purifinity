package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.util.Date;
import java.util.Set;

public class GenericRunMetrics extends AbstractMetrics {

    private Set<MetricParameter<?>> parameters;

    public GenericRunMetrics(String evaluatorId, Date time,
	    Set<MetricParameter<?>> parameters) {
	super(evaluatorId, time);
	this.parameters = parameters;
    }

    @Override
    public Set<MetricParameter<?>> getParameters() {
	return parameters;
    }

}
