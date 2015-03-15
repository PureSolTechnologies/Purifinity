package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.io.Serializable;

import com.puresoltechnologies.commons.math.GeneralValue;
import com.puresoltechnologies.commons.math.Parameter;

public class MetricValue<T extends Number & Serializable & Comparable<T>>
	extends GeneralValue<T> {

    @SuppressWarnings("unchecked")
    public static MetricValue<?> create(Parameter<?> foundParameter,
	    double value) {
	MetricValue<?> metricValue;
	if (foundParameter.getType().equals(Double.class)) {
	    metricValue = new MetricValue<Double>(value,
		    (Parameter<Double>) foundParameter);
	} else if (foundParameter.getType().equals(Float.class)) {
	    metricValue = new MetricValue<Float>((float) value,
		    (Parameter<Float>) foundParameter);
	} else if (foundParameter.getType().equals(Byte.class)) {
	    metricValue = new MetricValue<Byte>(((Double) value).byteValue(),
		    (Parameter<Byte>) foundParameter);
	} else if (foundParameter.getType().equals(Short.class)) {
	    metricValue = new MetricValue<Short>(((Double) value).shortValue(),
		    (Parameter<Short>) foundParameter);
	} else if (foundParameter.getType().equals(Integer.class)) {
	    metricValue = new MetricValue<Integer>(((Double) value).intValue(),
		    (Parameter<Integer>) foundParameter);
	} else if (foundParameter.getType().equals(Long.class)) {
	    metricValue = new MetricValue<Long>(((Double) value).longValue(),
		    (Parameter<Long>) foundParameter);
	} else {
	    metricValue = new MetricValue<Double>(value,
		    (Parameter<Double>) foundParameter);
	}
	return metricValue;
    }

    private static final long serialVersionUID = -4016034567082563563L;

    public MetricValue(T value, Parameter<T> property) {
	super(value, property);
    }

}
