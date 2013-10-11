package com.puresol.purifinity.coding.metrics.cocomo.intermediate;

import java.util.HashMap;
import java.util.Map;

public enum PersonellAttributes {

	ANALYST_CAPABILITY(1.46, 1.19, 1.00, 0.86, 0.71, null), //
	APPLICATION_EXPERIENCE(1.29, 1.13, 1.00, 0.91, 0.82, null), //
	SOFTWARE_ENGINEER_CAPABILITY(1.42, 1.17, 1.00, 0.86, 0.70, null), //
	VIRTUAL_MACHINE_EXPERIENCE(1.21, 1.10, 1.00, 0.90, null, null), //
	PROGRAMMING_EXPERIENCE(1.14, 1.07, 1.00, 0.95, null, null), ;

	private final Map<Rating, Double> ratings = new HashMap<>();

	private PersonellAttributes(Double veryLow, Double low, Double nominal,
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
