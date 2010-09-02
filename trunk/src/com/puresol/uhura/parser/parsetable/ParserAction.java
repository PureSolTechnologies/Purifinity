package com.puresol.uhura.parser.parsetable;

/**
 * This is a single state table entry for storing actions.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ParserAction {

	private final ActionType action;
	private final int parameter;
	private final boolean preferred;

	/**
	 * Constructor
	 * 
	 * @param action
	 *            to take when parsing.
	 * @param parameter
	 *            is the target state after the process. States small than 0
	 *            show no target states for Accept and Error.
	 */
	public ParserAction(ActionType action, int parameter) {
		super();
		this.action = action;
		this.parameter = parameter;
		this.preferred = false;
	}

	public ParserAction(ActionType action, int parameter, boolean preferred) {
		super();
		this.action = action;
		this.parameter = parameter;
		this.preferred = preferred;
	}

	/**
	 * @return the action
	 */
	public ActionType getAction() {
		return action;
	}

	/**
	 * @return the targetState
	 */
	public int getParameter() {
		return parameter;
	}

	public boolean isPreferred() {
		return preferred;
	}

	@Override
	public String toString() {
		String result = action.toString();
		if (parameter >= 0) {
			result += parameter;
		}
		if (preferred) {
			result += "*";
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
		result = prime * result + parameter;
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
		ParserAction other = (ParserAction) obj;
		if (action != other.action)
			return false;
		if (parameter != other.parameter)
			return false;
		return true;
	}

}
