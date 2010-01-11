package com.puresol.coding.java.parts;

import com.puresol.coding.java.keywords.PackageKeyword;
import com.puresol.coding.java.tokens.Dot;
import com.puresol.coding.java.tokens.IdLiteral;
import com.puresol.coding.java.tokens.Semicolon;
import com.puresol.parser.AbstractPart;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class PackageDeclaration extends AbstractPart {

    public PackageDeclaration(TokenStream tokenStream, int startPos) {
	super(tokenStream, startPos);
    }

    @Override
    public void scan()
	    throws PartDoesNotMatchException {
	processToken(PackageKeyword.class);
	processToken(IdLiteral.class);
	while (isToken(Dot.class)) {
	    processToken(Dot.class);
	    processToken(IdLiteral.class);
	}
	processToken(Semicolon.class);
    }
}
