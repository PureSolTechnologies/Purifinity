package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.io.Serializable;

import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.domain.ParameterWithArbitraryUnit;

public class EvaluationParameter<T extends Number & Serializable & Comparable<T>>
	extends ParameterWithArbitraryUnit<T> {

    private static final long serialVersionUID = 7257601246010446342L;

    public EvaluationParameter(String name, String unit, LevelOfMeasurement levelOfMeasurement, String description,
	    Class<T> type) {
	super(name, unit, levelOfMeasurement, description, type);
    }

}
