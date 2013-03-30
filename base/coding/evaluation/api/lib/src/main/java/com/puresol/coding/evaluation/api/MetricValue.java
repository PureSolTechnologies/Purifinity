package com.puresol.coding.evaluation.api;

import com.puresol.utils.math.GeneralValue;
import com.puresol.utils.math.ParameterWithArbitraryUnit;

public class MetricValue extends GeneralValue<Double> {

    private static final long serialVersionUID = -4016034567082563563L;

    public MetricValue(Double value, ParameterWithArbitraryUnit<Double> property) {
	super(value, property);
    }

}
