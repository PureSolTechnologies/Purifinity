package com.puresol.uhura.parser.statetable;

/**
 * This is a single state table entry for storing actions.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TableActionEntry {

	private final Action action;
	private final int targetState;

	/**
	 * Constructor
	 * 
	 * @param action
	 *            to take when parsing.
	 * @param targetState
	 *            is the target state after the process. States small than 0
	 *            show no target states for Accept and Error.
	 */
	public TableActionEntry(Action action, int targetState) {
		super();
		this.action = action;
		this.targetState = targetState;
	}

	/**
	 * @return the action
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * @return the targetState
	 */
	public int getTargetState() {
		return targetState;
	}

	@Override
	public String toString() {
		String result = action.toString();
		if (targetState >= 0) {
			result += targetState;
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + targetState;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TableActionEntry other = (TableActionEntry) obj;
		if (action != other.action)
			return false;
		if (targetState != other.targetState)
			return false;
		return true;
	}

}
