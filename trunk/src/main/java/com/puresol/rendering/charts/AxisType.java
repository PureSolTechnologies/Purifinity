package com.puresol.rendering.charts;

import com.puresol.exceptions.StrangeSituationException;

public enum AxisType {

	LINEAR, LOG;

	public double scale(double v) {
		switch (this) {
		case LINEAR:
			return v;
		case LOG:
			if (v < 0.0) {
				return Math.log10(-v);
			} else if (v > 0.0) {
				return Math.log10(v);
			} else {
				throw new IllegalArgumentException("Value is zero in log axis!");
			}
		default:
			throw new StrangeSituationException(
					"Axis type scaling is not defined!");
		}
	}

}
