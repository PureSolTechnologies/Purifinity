package com.puresol.coding.java.partdef;

import com.puresol.coding.java.tokendef.ID;
import com.puresol.coding.java.tokendef.PackageKeyword;
import com.puresol.parser.AbstractPart;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;
import com.puresol.parser.defaulttokens.Dot;
import com.puresol.parser.defaulttokens.Semicolon;

public class PackageDeclaration extends AbstractPart {

    public PackageDeclaration(TokenStream tokenStream, int startPos) {
	super(tokenStream, startPos);
    }

    @Override
    public void scan()
	    throws PartDoesNotMatchException {
	processToken(PackageKeyword.class);
	processToken(ID.class);
	while (isToken(Dot.class)) {
	    processToken(Dot.class);
	    processToken(ID.class);
	}
	processToken(Semicolon.class);
    }
}
