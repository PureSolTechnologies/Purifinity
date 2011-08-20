package com.puresol.coding.metrics.sloc;

/**
 * This enumeration lists all types of SLOC: blank lines, comment lines and
 * productive lines. All lines are part of physical lines.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum SLOCType {

	BLANK("bl"), COMMENT("com"), PRODUCTIVE("pro"), PHYSICAL("phy");

	private final String sign;

	private SLOCType(String sign) {
		this.sign = sign;
	}

	public String getSign() {
		return sign;
	}

}
