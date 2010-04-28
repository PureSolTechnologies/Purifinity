package com.puresol.coding.lang.java;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.source.parts.ClassDeclaration;
import com.puresol.coding.lang.java.source.parts.EnumDeclaration;
import com.puresol.coding.lang.java.source.parts.Import;
import com.puresol.coding.lang.java.source.parts.InterfaceDeclaration;
import com.puresol.coding.lang.java.source.parts.PackageDeclaration;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class JavaParser extends AbstractJavaParser {

    private static final long serialVersionUID = -5271390812159304045L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	try {
	    moveToNextVisible(0);
	} catch (EndOfTokenStreamException e) {
	    // this may happen if there is an empty file...
	    return;
	}
	expectPart(PackageDeclaration.class);
	while (acceptPart(Import.class) != null)
	    ;
	if (acceptPart(ClassDeclaration.class) != null) {
	} else if (acceptPart(InterfaceDeclaration.class) != null) {
	} else if (acceptPart(EnumDeclaration.class) != null) {
	} else {
	    throw new PartDoesNotMatchException(this);
	}
	finish(getFile().getName());
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FILE;
    }
}
