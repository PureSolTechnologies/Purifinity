package com.puresol.coding.lang.cpp;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.lang.Language;
import com.puresol.coding.lang.java.source.parts.ClassDeclaration;
import com.puresol.coding.lang.java.source.parts.EnumDeclaration;
import com.puresol.coding.lang.java.source.parts.Import;
import com.puresol.coding.lang.java.source.parts.InterfaceDeclaration;
import com.puresol.coding.lang.java.source.parts.PackageDeclaration;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class CPPParser extends AbstractSourceCodeParser {

    public CPPParser(TokenStream tokenStream) {
	setTokenStream(tokenStream);
	setStartPosition(0);
    }

    @Override
    public void scan() throws PartDoesNotMatchException {
	TokenStream tokenStream = getTokenStream();
	addCodeRange(Language.CPP, CodeRangeType.FILE, 0, tokenStream
		.getSize() - 1);

	try {
	    moveForward(0);
	} catch (EndOfTokenStreamException e) {
	    // this may happen if there is an empty file...
	    return;
	}
	processPart(PackageDeclaration.class);
	while (isPart(Import.class)) {
	    processPart(Import.class);
	}
	if (processPartIfPossible(ClassDeclaration.class)) {
	} else if (processPartIfPossible(InterfaceDeclaration.class)) {
	} else if (processPartIfPossible(EnumDeclaration.class)) {
	} else {
	    throw new PartDoesNotMatchException(this);
	}
    }
}
