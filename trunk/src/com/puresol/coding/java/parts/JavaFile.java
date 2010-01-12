package com.puresol.coding.java.parts;

import com.puresol.coding.AbstractSourceCodePart;
import com.puresol.coding.SourceCodeParser;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class JavaFile extends AbstractSourceCodePart {

    public JavaFile(SourceCodeParser parser, TokenStream tokenStream,
	    int startPos) {
	super(parser, tokenStream, startPos);
    }

    @Override
    public void scan() throws PartDoesNotMatchException {
	processPart(PackageDeclaration.class);
	while (isPart(Import.class)) {
	    processPart(Import.class);
	}
	processPart(ClassDeclaration.class);
    }
}
