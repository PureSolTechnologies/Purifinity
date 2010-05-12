package com.puresol.coding.lang.java.source.grammar;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.ExtendsKeyword;
import com.puresol.coding.lang.java.source.keywords.SuperKeyword;
import com.puresol.coding.lang.java.source.symbols.QuestionMark;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class TypeArgument extends AbstractJavaParser {

    private static final long serialVersionUID = 7523184950953085838L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptPart(Type.class) != null) {
	} else {
	    expectToken(QuestionMark.class);
	    if ((acceptToken(ExtendsKeyword.class) != null)
		    || (acceptToken(SuperKeyword.class) != null)) {
		expectPart(Type.class);
	    }
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }
}
