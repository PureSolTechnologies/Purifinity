package com.puresoltechnologies.purifinity.framework.store.db;

/**
 * This exception is thrown in cases of Titan connection isses.
 * 
 * @author Rick-Rainer Ludwig
 */
public class TitanConnectionException extends Exception {

	private static final long serialVersionUID = 5955783667764845579L;

	public TitanConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public TitanConnectionException(String message) {
		super(message);
	}

}
