package com.puresol.purifinity.utils.math;

/**
 * This enum contains all SI units plus currency to have a concise list of all
 * of these units.
 * 
 * Additionally, the units are enhanced by information about the unit sign.
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

	public String getName() {
		return name;
	}

	public String getSign() {
		return sign;
	}

	public String getDimension() {
		return dimension;
	}

	@Override
	public String toString() {
		return sign + " (" + name + " / " + dimension + ")";
	}
}
