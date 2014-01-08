package com.puresoltechnologies.commons.math;

/**
 * This enum contains all SI units to have a concise list of all of these units.
 * 
 * Additionally, the units are enhanced by information about the unit sign and a
 * dimension information.
 * 
 * The "International System of Units" is described in <a
 * href="http://en.wikipedia.org/wiki/SI_Units">
 * http://en.wikipedia.org/wiki/SI_Units </a>
 * 
 * @author Rick-Rainer Ludwig
 */
public enum SIUnit {

	AMPERE("A", "electric current"), //
	KELVIN("K", "temperature"), //
	SECOND("s", "time"), //
	METER("m", "length"), //
	KILOGRAM("kg", "mass"), //
	CANDELA("cd", "luminous intensity"), //
	MOL("mol", "amount of substance"), //
	;

	private final String name;
	private final String sign;
	private final String dimension;

	private SIUnit(String sign, String dimension) {
		name = name().toLowerCase();
		this.sign = sign;
		this.dimension = dimension;
	}

	/**
	 * Returns the name of the unit.
	 * 
	 * @return A {@link String} is returned.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the unit of the unit.
	 * 
	 * @return A {@link String} is returned.
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * Returns the dimension of the unit.
	 * 
	 * @return A {@link String} is returned.
	 */
	public String getDimension() {
		return dimension;
	}

	@Override
	public String toString() {
		return sign + " (" + name + " / " + dimension + ")";
	}
}
