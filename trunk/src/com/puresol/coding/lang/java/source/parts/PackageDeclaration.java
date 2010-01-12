package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.lang.java.source.keywords.PackageKeyword;
import com.puresol.coding.lang.java.source.literals.IdLiteral;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class PackageDeclaration extends AbstractSourceCodeParser {

	public PackageDeclaration(TokenStream tokenStream, int startPos) {
		super(tokenStream, startPos);
	}

	@Override
	public void scan() throws PartDoesNotMatchException {
		processToken(PackageKeyword.class);
		processToken(IdLiteral.class);
		while (isToken(Dot.class)) {
			processToken(Dot.class);
			processToken(IdLiteral.class);
		}
		processToken(Semicolon.class);
	}
}
