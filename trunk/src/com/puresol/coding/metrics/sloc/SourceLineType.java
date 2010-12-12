package com.puresol.coding.metrics.sloc;

/**
 * This enumeration lists all types of SLOC: blank lines, comment lines and
 * productive lines. All lines are part of physical lines.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum SourceLineType {

	BLANK("bl"), COMMENT("com"), PRODUCTIVE("pro"), PHYSICAL("phy");

	private final String sign;

	private SourceLineType(String sign) {
		this.sign = sign;
	}

	public String getSign() {
		return sign;
	}

}
