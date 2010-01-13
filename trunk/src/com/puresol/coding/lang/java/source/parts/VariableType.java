package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.lang.java.source.tokengroups.Primitives;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class VariableType extends AbstractSourceCodeParser {

	public VariableType(TokenStream tokenStream, int startPos) {
		super(tokenStream, startPos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void scan() throws PartDoesNotMatchException {
		if (isCurrentOneOf(Primitives.class)) {
			processOneOf(Primitives.class);
			return;
		} 
		processPart(ObjectType.class);
	}

}
