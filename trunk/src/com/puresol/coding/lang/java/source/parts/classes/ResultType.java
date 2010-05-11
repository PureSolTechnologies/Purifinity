package com.puresol.coding.lang.java.source.parts.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.VoidKeyword;
import com.puresol.coding.lang.java.source.parts.types_values_variables.Type;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ResultType extends AbstractJavaParser {

    private static final long serialVersionUID = 7410581812232089806L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptPart(Type.class) != null) {

	} else {
	    expectToken(VoidKeyword.class);
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
