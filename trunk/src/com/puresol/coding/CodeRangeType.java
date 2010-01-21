/***************************************************************************
 *
 *   CodeRangeType.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding;

/**
 * This enum lists all kind of code ranges which are supported.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum CodeRangeType {
    FILE("file"), CLASS("class"), INTERFACE("interface"), ENUMERATION(
	    "enumerator"), CONSTRUCTOR("constructor"), METHOD("method"), FUNCTION(
	    "function");

    private String name;

    private CodeRangeType(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public boolean isRunnableCodeSegment() {
	if (this == METHOD) {
	    return true;
	}
	if (this == FUNCTION) {
	    return true;
	}
	return false;
    }
}
