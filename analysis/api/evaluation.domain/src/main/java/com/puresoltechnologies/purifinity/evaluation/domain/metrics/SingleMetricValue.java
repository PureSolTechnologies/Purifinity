package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.io.Serializable;

import com.puresoltechnologies.commons.domain.Parameter;

public class SingleMetricValue<T extends Number & Serializable & Comparable<T>> extends MetricValue<T> {

    private static final long serialVersionUID = 7860774640032751278L;

    public SingleMetricValue(T value, Parameter<T> parameter) {
	super(value, parameter);
    }

}
