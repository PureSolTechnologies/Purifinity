package com.puresol.uhura.parser.parsingtable;

/**
 * This enumeration contains all possible parser actions.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum ActionType {

	SHIFT("s"), REDUCE("r"), ACCEPT("acc"), ERROR("err"), GOTO("");

	private final String text;

	private ActionType(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
