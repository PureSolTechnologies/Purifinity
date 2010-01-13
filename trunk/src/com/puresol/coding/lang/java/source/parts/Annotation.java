package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class Annotation extends AbstractSourceCodeParser {

	public Annotation(TokenStream tokenStream, int startPos) {
		super(tokenStream, startPos);
	}

	@Override
	public void scan() throws PartDoesNotMatchException {
		processPart(AnnotationIdentifier.class);
		if (isToken(LParen.class)) {
			skipNested(LParen.class, RParen.class);
		}
	}

}
