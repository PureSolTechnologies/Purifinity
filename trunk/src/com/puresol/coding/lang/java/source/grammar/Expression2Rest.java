package com.puresol.coding.lang.java.source.grammar;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.InstanceofKeyword;
import com.puresol.coding.lang.java.source.symbols.Colon;
import com.puresol.coding.lang.java.source.symbols.QuestionMark;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Expression2Rest extends AbstractJavaParser {

    private static final long serialVersionUID = 6464754895556318548L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptPart(InfixOp.class) != null) {
	    expectPart(Expression3.class);
	    while (acceptPart(InfixOp.class) != null) {
		expectPart(Expression3.class);
	    }
	} else {
	    expectPart(Expression3.class);
	    expectToken(InstanceofKeyword.class);
	    expectPart(Type.class);
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
