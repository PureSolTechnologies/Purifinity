package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.lang.fortran.source.literals.IdLiteral;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class ObjectType extends AbstractSourceCodeParser {

	public ObjectType(TokenStream tokenStream, int startPos) {
		super(tokenStream, startPos);
	}

	@Override
	public void scan() throws PartDoesNotMatchException {
		processToken(IdLiteral.class);
		while (processTokenIfPossible(Dot.class)) {
			processToken(IdLiteral.class);
		}
		processPartIfPossible(Generic.class);
	}

}
