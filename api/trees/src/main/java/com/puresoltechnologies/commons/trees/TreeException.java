package com.puresoltechnologies.commons.trees;

/**
 * This exception is thrown if a tree function or action is not suitable or
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

	public TreeException(String message, Throwable cause) {
		super(message, cause);
	}

}
