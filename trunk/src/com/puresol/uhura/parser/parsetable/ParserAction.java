package com.puresol.uhura.parser.parsetable;

import java.io.Serializable;

/**
 * This is a single state table entry for storing actions.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ParserAction implements Serializable {

	private static final long serialVersionUID = -3440478384808031825L;

	private final ActionType action;
	private final int parameter;
	private final int hashCode;

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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + parameter;
		hashCode = result;
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

	@Override
	public String toString() {
		String result = action.toString();
		if (parameter >= 0) {
			result += parameter;
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
		return hashCode;
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
		if (this.hashCode != other.hashCode) {
			return false;
		}
		if (action != other.action)
			return false;
		if (parameter != other.parameter)
			return false;
		return true;
	}

}
