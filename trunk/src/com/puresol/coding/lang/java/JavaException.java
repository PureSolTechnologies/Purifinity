package com.puresol.coding.lang.java;

public class JavaException extends Exception {

	private static final long serialVersionUID = -6059552402763963014L;

	public JavaException(String message, Throwable cause) {
		super(message, cause);
	}

	public JavaException(String message) {
		super(message);
	}

	public JavaException(Throwable cause) {
		super(cause);
	}

}
