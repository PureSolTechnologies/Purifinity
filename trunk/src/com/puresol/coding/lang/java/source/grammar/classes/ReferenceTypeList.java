package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.cpp.source.symbols.Comma;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.ClassOrInterfaceType;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ReferenceTypeList extends AbstractJavaParser {

    private static final long serialVersionUID = 7410581812232089806L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectPart(ClassOrInterfaceType.class);
	while (acceptToken(Comma.class) != null) {
	    expectPart(ClassOrInterfaceType.class);
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
