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
}
