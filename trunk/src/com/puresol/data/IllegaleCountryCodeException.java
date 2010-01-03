package com.puresol.data;

/**
 * This exception is thrown in case of the usage of a illegal ISO country code.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class IllegaleCountryCodeException extends IllegalArgumentException {

	private static final long serialVersionUID = -5137347940995440970L;

	public IllegaleCountryCodeException() {
		super();
	}

	public IllegaleCountryCodeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public IllegaleCountryCodeException(String arg0) {
		super(arg0);
	}

	public IllegaleCountryCodeException(Throwable arg0) {
		super(arg0);
	}

}
