package com.puresol.purifinity.coding.evaluation.api;

import java.util.Arrays;

import com.puresol.commons.utils.math.statistics.Statistics;

public class QualityLevel extends Statistics {

	private static final long serialVersionUID = -1585502470333567684L;

	public static double getLevel(SourceCodeQuality quality) {
		double level;
		switch (quality) {
		case HIGH:
			level = 1.0;
			break;
		case MEDIUM:
			level = 0.5;
			break;
		case LOW:
			level = 0.0;
			break;
		default:
			throw new IllegalArgumentException(
					"Quality '"
							+ quality.name()
							+ "' is not supported or know. The quality level needs to be specfied explicitly. UNSPECIFIED is not supported.");
		}
		return level;
	}

	public QualityLevel(SourceCodeQuality quality) {
		super(Arrays.asList(getLevel(quality)));
	}

	public QualityLevel(double level) {
		super(Arrays.asList(level));
	}

	public double getLevel() {
		return getAvg();
	}
}
