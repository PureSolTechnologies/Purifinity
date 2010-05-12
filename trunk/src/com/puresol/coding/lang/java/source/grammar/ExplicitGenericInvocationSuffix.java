package com.puresol.coding.lang.java.source.grammar;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.SuperKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ExplicitGenericInvocationSuffix extends AbstractJavaParser {

    private static final long serialVersionUID = 6464754895556318548L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptToken(SuperKeyword.class) != null) {
	    expectPart(SuperSuffix.class);
	} else {
	    expectPart(Identifier.class);
	    expectPart(Arguments.class);
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
