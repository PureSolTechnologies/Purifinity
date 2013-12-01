package com.puresoltechnologies.purifinity.coding.evaluation.api;

import com.puresoltechnologies.commons.math.GeneralValue;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;

public class MetricValue extends GeneralValue<Double> {

	private static final long serialVersionUID = -4016034567082563563L;

	public MetricValue(Double value, ParameterWithArbitraryUnit<Double> property) {
		super(value, property);
	}

}
