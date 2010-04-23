package com.puresol.coding.lang.java;

import com.puresol.coding.lang.java.source.coderanges.JavaFile;
import com.puresol.coding.lang.java.source.parts.ClassDeclaration;
import com.puresol.coding.lang.java.source.parts.EnumDeclaration;
import com.puresol.coding.lang.java.source.parts.Import;
import com.puresol.coding.lang.java.source.parts.InterfaceDeclaration;
import com.puresol.coding.lang.java.source.parts.PackageDeclaration;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class JavaParser extends AbstractJavaParser {

    private static final long serialVersionUID = -5271390812159304045L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	TokenStream tokenStream = getTokenStream();
	addCodeRange(new JavaFile(tokenStream.getFile().getName(), tokenStream,
		0, tokenStream.getSize() - 1));

	try {
	    moveToNextVisible(0);
	} catch (EndOfTokenStreamException e) {
	    // this may happen if there is an empty file...
	    return;
	}
	expectPart(PackageDeclaration.class);
	while (isPart(Import.class)) {
	    expectPart(Import.class);
	}
	if (acceptPart(ClassDeclaration.class)) {
	} else if (acceptPart(InterfaceDeclaration.class)) {
	} else if (acceptPart(EnumDeclaration.class)) {
	} else {
	    throw new PartDoesNotMatchException(this);
	}
    }
}
