package com.puresol.purifinity.coding.metrics.cocomo.intermediate;

import java.util.HashMap;
import java.util.Map;

public enum ProjectAttributes {

	APPLICATION_OF_SOFTWARE_ENGINEERING_METHODS(1.24, 1.10, 1.00, 0.91, 0.82,
			null), //
	USE_OF_SOFTWARE_TOOLS(1.24, 1.10, 1.00, 0.91, 0.83, null), //
	REQUIRED_DEVELOPMENT_SCHEDULE(1.23, 1.08, 1.00, 1.04, 1.10, null), //
	;

	private final Map<Rating, Double> ratings = new HashMap<>();

	private ProjectAttributes(Double veryLow, Double low, Double nominal,
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
