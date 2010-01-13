package com.puresol.parser;

public class EndOfTokenStreamException extends Exception {

	private static final long serialVersionUID = -7955391541534734136L;

	public EndOfTokenStreamException(Parser part) {
		super("Part '" + part.getClass().getName() + "' reaches to the end...");
	}

}
