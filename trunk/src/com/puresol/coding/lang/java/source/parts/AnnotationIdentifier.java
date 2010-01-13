package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.lang.fortran.source.literals.IdLiteral;
import com.puresol.coding.lang.java.source.symbols.At;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class AnnotationIdentifier extends AbstractSourceCodeParser {

	public AnnotationIdentifier(TokenStream tokenStream, int startPos) {
		super(tokenStream, startPos);
	}

	@Override
	public void scan() throws PartDoesNotMatchException {
		processToken(At.class);
		processToken(IdLiteral.class);
	}

}
