package com.puresol.coding.lang.java.source.grammar;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.coding.lang.java.source.symbols.GreaterThan;
import com.puresol.coding.lang.java.source.symbols.LessThan;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class TypeArguments extends AbstractJavaParser {

    private static final long serialVersionUID = 7523184950953085838L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectToken(LessThan.class);
	expectPart(TypeArgument.class);
	while (acceptToken(Comma.class) != null) {
	    expectPart(TypeArgument.class);
	}
	expectToken(GreaterThan.class);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

    public String getVariableTypeName() {
	return getContinuousText();
    }
}
