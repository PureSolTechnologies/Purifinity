package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.BitOr;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * inclusiveOrExpression : exclusiveOrExpression ('|' exclusiveOrExpression )* ;
 * 
 * Fix: Chained inclusive ORs are allowed.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class InclusiveOrExpression extends AbstractJavaParser {

    private static final long serialVersionUID = 6464754895556318548L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectPart(ExclusiveOrExpression.class);
	if (acceptToken(BitOr.class) != null) {
	    expectPart(InclusiveOrExpression.class);
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
