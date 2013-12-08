package com.puresoltechnologies.commons.trees.api;

/**
 * This exception is thrown if a AST function or action is not suitable or
 * invalid.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TreeException extends Exception {

	private static final long serialVersionUID = 5048301833347363159L;

	public TreeException(String message) {
		super(message);
	}

}
