package com.puresol.uhura.ast;

/**
 * This exception is thrown if a AST function or action is not suitable or
 * invalid.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ASTException extends Exception {

	private static final long serialVersionUID = 5048301833347363159L;

	public ASTException(String message) {
		super(message);
	}

}
