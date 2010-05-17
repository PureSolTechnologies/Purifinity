package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Equal;
import com.puresol.coding.lang.java.source.symbols.Unequal;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * equalityExpression : instanceOfExpression ( ( '==' | '!=' )
 * instanceOfExpression )* ;
 * 
 * Fix: Chained equality expressions are allowed.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EqualityExpression extends AbstractJavaParser {

    private static final long serialVersionUID = 6464754895556318548L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectPart(InstanceOfExpression.class);
	if (acceptToken(Equal.class) != null) {
	    expectPart(EqualityExpression.class);
	} else if (acceptToken(Unequal.class) != null) {
	    expectPart(EqualityExpression.class);
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
