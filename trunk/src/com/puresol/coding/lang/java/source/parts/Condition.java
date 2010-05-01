package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.ExclamationMark;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.coding.lang.java.source.tokengroups.Conditionals;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Condition extends AbstractJavaParser {

    private static final long serialVersionUID = 6464754895556318548L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptToken(ExclamationMark.class) != null) {
	    if (acceptPart(Condition.class) == null) {
		expectPart(Expression.class);
	    }
	} else if (acceptToken(LParen.class) != null) {
	    expectPart(Condition.class);
	    expectToken(RParen.class);
	} else {
	    expectPart(Expression.class);
	    expectOneTokenOf(Conditionals.DEFINITIONS);
	    expectPart(Expression.class);
	}

	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
