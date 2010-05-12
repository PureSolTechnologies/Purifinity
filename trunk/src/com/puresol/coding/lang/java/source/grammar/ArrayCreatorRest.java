package com.puresol.coding.lang.java.source.grammar;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.LRectBracket;
import com.puresol.coding.lang.java.source.symbols.RRectBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ArrayCreatorRest extends AbstractJavaParser {

    private static final long serialVersionUID = 6464754895556318548L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectToken(LRectBracket.class);
	if (acceptToken(RRectBracket.class) != null) {
	    while (acceptToken(LRectBracket.class) != null) {
		expectToken(RRectBracket.class);
	    }
	    expectPart(ArrayInitializer.class);
	} else {
	    expectPart(Expression.class);
	    expectToken(RRectBracket.class);
	    while (acceptToken(LRectBracket.class) != null) {
		if (acceptPart(Expression.class) != null) {
		    expectToken(RRectBracket.class);
		} else {
		    expectToken(RRectBracket.class);
		    break;
		}
	    }
	    while (acceptToken(LRectBracket.class) != null) {
		expectToken(RRectBracket.class);
	    }
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
