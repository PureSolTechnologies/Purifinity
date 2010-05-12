package com.puresol.coding.lang.java.source.grammar;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.parts.expressions.AssignmentExpression;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Expression2 extends AbstractJavaParser {

    private static final long serialVersionUID = 6464754895556318548L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectPart(Expression3.class);
	acceptPart(Expression2Rest.class);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
