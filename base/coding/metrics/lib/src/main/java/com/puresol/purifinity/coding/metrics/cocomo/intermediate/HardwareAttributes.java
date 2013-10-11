package com.puresol.purifinity.coding.metrics.cocomo.intermediate;

import java.util.HashMap;
import java.util.Map;

public enum HardwareAttributes {

	RUNTIME_PERFORMANCE_CONSTRAINTS(null, null, 1.00, 1.11, 1.30, 1.66), //
	MEMORY_CONSTRAINTS(null, null, 1.00, 1.06, 1.21, 1.56), //
	VOLATILITY_OF_THE_VIRTUAL_MACHINE_ENVIRONMENT(null, 0.87, 1.00, 1.15, 1.30,
			null), //
	REQUIRED_TURNABOUT_TIME(null, 0.87, 1.00, 1.07, 1.15, null), //
	;

	private final Map<Rating, Double> ratings = new HashMap<>();

	private HardwareAttributes(Double veryLow, Double low, Double nominal,
			Double high, Double veryHigh, Double extraHigh) {
		ratings.put(Rating.VERY_LOW, veryLow);
		ratings.put(Rating.LOW, low);
		ratings.put(Rating.NOMINAL, nominal);
		ratings.put(Rating.HIGH, high);
		ratings.put(Rating.VERY_HIGH, veryHigh);
		ratings.put(Rating.EXTRA_HIGH, extraHigh);
	}

	public double getFactor(Rating rating) {
		return ratings.get(rating);
	}
}
