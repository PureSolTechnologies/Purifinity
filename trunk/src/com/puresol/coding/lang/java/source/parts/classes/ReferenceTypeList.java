package com.puresol.coding.lang.java.source.parts.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.cpp.source.symbols.Comma;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.parts.types_values_variables.ReferenceType;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ReferenceTypeList extends AbstractJavaParser {

    private static final long serialVersionUID = 7410581812232089806L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectPart(ReferenceType.class);
	while (acceptToken(Comma.class) != null) {
	    expectPart(ReferenceType.class);
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
