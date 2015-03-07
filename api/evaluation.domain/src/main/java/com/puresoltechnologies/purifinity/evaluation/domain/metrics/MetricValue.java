package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.io.Serializable;

import com.puresoltechnologies.commons.math.GeneralValue;
import com.puresoltechnologies.commons.math.Parameter;

public class MetricValue<T extends Number & Serializable & Comparable<T>>
	extends GeneralValue<T> {

    private static final long serialVersionUID = -4016034567082563563L;

    public MetricValue(T value, Parameter<T> property) {
	super(value, property);
    }

}
