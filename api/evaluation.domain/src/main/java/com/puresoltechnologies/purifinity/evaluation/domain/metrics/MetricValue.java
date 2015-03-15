package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.math.GeneralValue;
import com.puresoltechnologies.commons.math.Parameter;

public class MetricValue<T extends Number & Serializable & Comparable<T>>
	extends GeneralValue<T> {

    @SuppressWarnings("unchecked")
    public static MetricValue<?> create(Parameter<?> foundParameter,
	    double value) {
	if (foundParameter.getType().equals(Double.class)) {
	    return new MetricValue<Double>(value,
		    (Parameter<Double>) foundParameter);
	} else if (foundParameter.getType().equals(Float.class)) {
	    return new MetricValue<Float>((float) value,
		    (Parameter<Float>) foundParameter);
	} else if (foundParameter.getType().equals(Byte.class)) {
	    return new MetricValue<Byte>(((Double) value).byteValue(),
		    (Parameter<Byte>) foundParameter);
	} else if (foundParameter.getType().equals(Short.class)) {
	    return new MetricValue<Short>(((Double) value).shortValue(),
		    (Parameter<Short>) foundParameter);
	} else if (foundParameter.getType().equals(Integer.class)) {
	    return new MetricValue<Integer>(((Double) value).intValue(),
		    (Parameter<Integer>) foundParameter);
	} else if (foundParameter.getType().equals(Long.class)) {
	    return new MetricValue<Long>(((Double) value).longValue(),
		    (Parameter<Long>) foundParameter);
	} else {
	    return new MetricValue<Double>(value,
		    (Parameter<Double>) foundParameter);
	}
    }

    private static final long serialVersionUID = -4016034567082563563L;

    @JsonCreator
    public MetricValue(@JsonProperty("value") T value,
	    @JsonProperty("parameter") Parameter<T> parameter) {
	super(value, parameter);
    }

}
