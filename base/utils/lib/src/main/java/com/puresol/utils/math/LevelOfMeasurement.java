package com.puresol.utils.math;

/**
 * This enum specifies of which level or measurement a certain parameter is. The
 * definition can be found at:
 * 
 * <a href="http://en.wikipedia.org/wiki/Level_of_measurement">
 * http://en.wikipedia.org/wiki/Level_of_measurement </a>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum LevelOfMeasurement {

	NOMINAL, ORDINAL, INTERVAL, RATIO;

	@Override
	public String toString() {
		return name().toLowerCase();
	};
}
