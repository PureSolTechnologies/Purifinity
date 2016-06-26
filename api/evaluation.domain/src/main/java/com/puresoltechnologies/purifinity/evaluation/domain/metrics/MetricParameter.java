package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;

/**
 * This is a special interface for metrics parameters, because these need to
 * have {@link Number} values which are {@link Comparable}. Additionally, they
 * may be moved remotely, so they also need to be {@link Serializable}.
 * 
 * @author Rick-Rainer Ludwig
 *
 * @param <T>
 *            is the actual type of the metrics value.
 */
public class MetricParameter<T extends Number & Serializable & Comparable<T>> extends EvaluationParameter<T> {

    public static MetricParameter<?> create(String parameterName, String parameterUnit,
	    LevelOfMeasurement levelOfMeasurement, String parameterDescription, Class<?> type) {
	if (type.equals(Double.class)) {
	    return new MetricParameter<>(parameterName, parameterUnit, levelOfMeasurement, parameterDescription,
		    Double.class);
	} else if (type.equals(Float.class)) {
	    return new MetricParameter<>(parameterName, parameterUnit, levelOfMeasurement, parameterDescription,
		    Float.class);
	} else if (type.equals(Byte.class)) {
	    return new MetricParameter<>(parameterName, parameterUnit, levelOfMeasurement, parameterDescription,
		    Byte.class);
	} else if (type.equals(Short.class)) {
	    return new MetricParameter<>(parameterName, parameterUnit, levelOfMeasurement, parameterDescription,
		    Short.class);
	} else if (type.equals(Integer.class)) {
	    return new MetricParameter<>(parameterName, parameterUnit, levelOfMeasurement, parameterDescription,
		    Integer.class);
	} else if (type.equals(Long.class)) {
	    return new MetricParameter<>(parameterName, parameterUnit, levelOfMeasurement, parameterDescription,
		    Long.class);
	} else {
	    return new MetricParameter<>(parameterName, parameterUnit, levelOfMeasurement, parameterDescription,
		    Double.class);
	}
    }

    private static final long serialVersionUID = -2759994739756661433L;

    @JsonCreator
    public MetricParameter(@JsonProperty("name") String name, @JsonProperty("unit") String unit,
	    @JsonProperty("levelOfMeasurement") LevelOfMeasurement levelOfMeasurement,
	    @JsonProperty("description") String description, @JsonProperty("type") Class<T> type) {
	super(name, unit, levelOfMeasurement, description, type);
    }

}
