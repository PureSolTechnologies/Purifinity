package com.puresol.coding.java.parts;

import com.puresol.parser.AbstractPart;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class JavaFile extends AbstractPart {

    public JavaFile(TokenStream tokenStream, int startPos) {
	super(tokenStream, startPos);
    }

    @Override
    public void scan() throws PartDoesNotMatchException {
	processPart(PackageDeclaration.class);
    }
}
