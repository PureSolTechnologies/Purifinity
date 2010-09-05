package com.puresol.uhura.parser.lr;

import java.io.Serializable;

public class BacktrackLocation implements Serializable {

	private static final long serialVersionUID = -2641716613348317852L;

	private final int step;
	private final int stateId;
	private final int lastAlternative;

	public BacktrackLocation(int step, int stateId, int lastAlternative) {
		super();
		this.step = step;
		this.stateId = stateId;
		this.lastAlternative = lastAlternative;
	}

	/**
	 * @return the step
	 */
	public int getStep() {
		return step;
	}

	/**
	 * @return the stateId
	 */
	public int getStateId() {
		return stateId;
	}

	/**
	 * @return the lastAlternative
	 */
	public int getLastAlternative() {
		return lastAlternative;
	}

}
