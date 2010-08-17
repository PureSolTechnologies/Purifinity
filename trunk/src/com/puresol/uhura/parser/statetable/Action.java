package com.puresol.uhura.parser.statetable;

/**
 * This enumeration contains all possible parser actions.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum Action {

	SHIFT("s"), REDUCE("r"), ACCEPT("acc"), ERROR("err"), GOTO("");

	private final String text;

	private Action(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
