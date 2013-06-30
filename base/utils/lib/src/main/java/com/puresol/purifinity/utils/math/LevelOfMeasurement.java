package com.puresol.purifinity.utils.math;

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

	public boolean isAtLeast(LevelOfMeasurement level) {
		LevelOfMeasurement[] values = values();
		int me = 0;
		int other = values.length - 1;
		for (int i = 0; i < values.length; i++) {
			LevelOfMeasurement value = values[i];
			if (this == value) {
				me = i;
			}
			if (level == value) {
				other = i;
			}
		}
		return (me >= other);
	}
}
