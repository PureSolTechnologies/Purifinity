package com.puresol.purifinity.uhura.ust;

/**
 * This exception is thrown by {@link AbstractUSTNode} classes which have
 * complaints about parents, children or what so ever which does not fit into
 * the UST.
 * 
 * @author Rick-Rainer Ludwig
 */
public class UniversalSyntaxTreeException extends Exception {

	private static final long serialVersionUID = -977915535666544669L;

	public UniversalSyntaxTreeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UniversalSyntaxTreeException(String message) {
		super(message);
	}

}
