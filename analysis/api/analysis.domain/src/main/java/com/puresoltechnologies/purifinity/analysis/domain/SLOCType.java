package com.puresoltechnologies.purifinity.analysis.domain;

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
    private final String label;

    private SLOCType(String sign) {
	this.sign = sign;
	this.label = sign + "LOC";
    }

    public String getSign() {
	return sign;
    }

    public String getLabel() {
	return label;
    }
}
