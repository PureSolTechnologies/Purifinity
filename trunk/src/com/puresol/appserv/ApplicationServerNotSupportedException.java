package com.puresol.appserv;

public class ApplicationServerNotSupportedException extends Exception {

	private static final long serialVersionUID = 1L;

	public ApplicationServerNotSupportedException(int typeID) {
		super("Application server with type id '" + typeID
				+ "' is not supported!");
	}
}
