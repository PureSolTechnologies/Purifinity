package com.puresol.purifinity.coding.lang.fortran;

public class FortranException extends Exception {

	private static final long serialVersionUID = -6059552402763963014L;

	public FortranException(String message, Throwable cause) {
		super(message, cause);
	}

	public FortranException(String message) {
		super(message);
	}

	public FortranException(Throwable cause) {
		super(cause);
	}

}
