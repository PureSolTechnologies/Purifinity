package com.puresol.appserv;

public class InternalApplicationServerException extends Exception {

	private static final long serialVersionUID = 1L;

	public InternalApplicationServerException() {
		super("An internal error occured within the application server!");
	}
}
