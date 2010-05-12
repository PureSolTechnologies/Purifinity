package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ConstructorDeclaration extends AbstractJavaParser {

    private static final long serialVersionUID = -5105706064635403458L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	acceptPart(ConstructorModifiers.class);
	expectPart(ConstructorDeclarator.class);
	acceptPart(Throws.class);
	expectPart(ConstructorBody.class);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.CONSTRUCTOR;
    }

}
