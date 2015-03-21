package com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate;

import java.util.HashMap;
import java.util.Map;

public abstract class IntermediateCOCOMOAttribute {

    public static IntermediateCOCOMOAttribute valueOf(String name) {
	IntermediateCOCOMOAttribute attribute = HardwareAttributes
		.valueOf(name);
	if (attribute != null) {
	    return attribute;
	}
	attribute = PersonellAttributes.valueOf(name);
	if (attribute != null) {
	    return attribute;
	}
	attribute = ProductAttributes.valueOf(name);
	if (attribute != null) {
	    return attribute;
	}
	attribute = ProjectAttributes.valueOf(name);
	if (attribute != null) {
	    return attribute;
	}
	return null;
    }

    private final String name;
    private final Map<Rating, Double> ratings = new HashMap<>();

    protected IntermediateCOCOMOAttribute(String name, Double veryLow,
	    Double low, Double nominal, Double high, Double veryHigh,
	    Double extraHigh) {
	this.name = name;
	ratings.put(Rating.VERY_LOW, veryLow);
	ratings.put(Rating.LOW, low);
	ratings.put(Rating.NOMINAL, nominal);
	ratings.put(Rating.HIGH, high);
	ratings.put(Rating.VERY_HIGH, veryHigh);
	ratings.put(Rating.EXTRA_HIGH, extraHigh);
    }

    public final boolean hasRating(Rating rating) {
	return ratings.get(rating) != null;
    }

    public final double getFactor(Rating rating) {
	Double factor = ratings.get(rating);
	if (factor == null) {
	    throw new IllegalArgumentException("There is no rating '"
		    + rating.name() + "' for '" + getClass().getSimpleName()
		    + "'.");
	}
	return factor;
    }

    public final String getName() {
	return name;
    }
}
