package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.source.parts.VariableType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.ClassType;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ExceptionType extends AbstractJavaParser {

    private static final long serialVersionUID = 7410581812232089806L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptPart(ClassType.class) != null) {

	} else {
	    expectPart(VariableType.class);
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
