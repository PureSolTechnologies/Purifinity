package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class ConstructorModifiers extends AbstractSourceCodeParser {

	public ConstructorModifiers(TokenStream tokenStream, int startPos) {
		super(tokenStream, startPos);
	}

	@Override
	public void scan() throws PartDoesNotMatchException {
		while (isCurrentOneOf(com.puresol.coding.lang.java.source.tokengroups.ConstructorModifiers.class)) {
			processOneOf(com.puresol.coding.lang.java.source.tokengroups.ConstructorModifiers.class);
		}
	}

}
