package com.puresol.coding.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.java.source.keywords.ImportKeyword;
import com.puresol.coding.java.source.literals.IdLiteral;
import com.puresol.coding.java.source.symbols.Dot;
import com.puresol.coding.java.source.symbols.Semicolon;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class Import extends AbstractSourceCodeParser {

	public Import(TokenStream tokenStream, int startPos) {
		super(tokenStream, startPos);
	}

	@Override
	public void scan() throws PartDoesNotMatchException {
		processToken(ImportKeyword.class);
		processToken(IdLiteral.class);
		while (isToken(Dot.class)) {
			processToken(Dot.class);
			processToken(IdLiteral.class);
		}
		processToken(Semicolon.class);
	}
}
