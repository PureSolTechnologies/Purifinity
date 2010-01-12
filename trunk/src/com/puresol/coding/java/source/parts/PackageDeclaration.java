package com.puresol.coding.java.source.parts;

import com.puresol.coding.AbstractSourceCodePart;
import com.puresol.coding.SourceCodeParser;
import com.puresol.coding.java.source.keywords.PackageKeyword;
import com.puresol.coding.java.source.literals.IdLiteral;
import com.puresol.coding.java.source.symbols.Dot;
import com.puresol.coding.java.source.symbols.Semicolon;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class PackageDeclaration extends AbstractSourceCodePart {

    public PackageDeclaration(SourceCodeParser parser,
	    TokenStream tokenStream, int startPos) {
	super(parser, tokenStream, startPos);
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
