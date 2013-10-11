package com.puresol.purifinity.coding.metrics.cocomo.intermediate;

import java.util.HashMap;
import java.util.Map;

public enum ProductAttributes {

	REQUIRED_SOFTWARE_RELIABILITY(0.75, 0.88, 1.00, 1.15, 1.40, null), //
	SIZE_OF_APPLICATION_DATABASE(null, 0.94, 1.00, 1.08, 1.16, null), //
	COMPLEXITY_OF_THE_PRODUCT(0.70, 0.85, 1.00, 1.15, 1.30, 1.65), //
	;

	private final Map<Rating, Double> ratings = new HashMap<>();

	private ProductAttributes(Double veryLow, Double low, Double nominal,
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
