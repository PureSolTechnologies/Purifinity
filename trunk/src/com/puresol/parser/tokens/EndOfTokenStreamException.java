package com.puresol.parser.tokens;

import com.puresol.parser.Parser;

public class EndOfTokenStreamException extends Exception {

	private static final long serialVersionUID = -7955391541534734136L;

	public EndOfTokenStreamException(Parser part) {
		super("Part '" + part.getClass().getName()
				+ "' reached the end of the token stream!");
	}

	public EndOfTokenStreamException(TokenStreamIterator iterator) {
		super("Iterator '" + iterator.getClass().getName()
				+ "' reached the end of the token stream!");
	}

}
