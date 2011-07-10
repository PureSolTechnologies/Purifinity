package com.puresol.uhura.parser;

public class ParserException extends Exception {

	private static final long serialVersionUID = 6292645686476419232L;

	public ParserException(String message) {
		super(message);
	}

	public ParserException(String message, Throwable cause) {
		super(message, cause);
	}

}
