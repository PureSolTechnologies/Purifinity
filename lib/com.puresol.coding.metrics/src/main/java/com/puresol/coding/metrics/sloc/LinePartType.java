package com.puresol.coding.metrics.sloc;

import com.puresol.data.Identifiable;

/**
 * This enumeration lists all types of SLOC: blank lines, comment lines and
 * productive lines. All lines are part of physical lines.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum LinePartType implements Identifiable {

    BLANK("bl"), COMMENT("com"), PRODUCTIVE("pro"), PHYSICAL("phy");

    private final String sign;

    private LinePartType(String sign) {
	this.sign = sign;
    }

    @Override
    public final String getIdentifier() {
	return sign;
    }

}
