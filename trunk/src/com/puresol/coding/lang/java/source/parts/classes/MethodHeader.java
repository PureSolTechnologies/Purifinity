package com.puresol.coding.lang.java.source.parts.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class MethodHeader extends AbstractJavaParser {

    private static final long serialVersionUID = 7410581812232089806L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	acceptPart(MethodModifiers.class);
	acceptPart(TypeParameters.class);
	expectPart(ResultType.class);
	expectPart(MethodDeclarator.class);
	acceptPart(Throws.class);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
