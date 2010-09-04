package com.puresol.uhura.parser.parsetable;

import java.io.Serializable;

/**
 * This enumeration contains all possible parser actions.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum ActionType implements Serializable {

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
