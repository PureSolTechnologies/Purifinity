package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.io.Serializable;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;

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
public class MetricParameter<T extends Number & Serializable & Comparable<T>>
	extends ParameterWithArbitraryUnit<T> {

    public static MetricParameter<?> create(String parameterName,
	    String parameterUnit, LevelOfMeasurement levelOfMeasurement,
	    String parameterDescription, Class<?> type) {
	// TODO Auto-generated method stub
	return null;
    }

    private static final long serialVersionUID = -2759994739756661433L;

    public MetricParameter(String name, String unit,
	    LevelOfMeasurement levelOfMeasurement, String description,
	    Class<T> type) {
	super(name, unit, levelOfMeasurement, description, type);
    }

}
