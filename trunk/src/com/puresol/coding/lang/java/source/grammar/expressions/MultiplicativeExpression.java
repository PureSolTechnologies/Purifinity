package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Percent;
import com.puresol.coding.lang.java.source.symbols.Slash;
import com.puresol.coding.lang.java.source.symbols.Star;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * multiplicativeExpression : unaryExpression ( ( '*' | '/' | '%' )
 * unaryExpression )* ;
 * 
 * Fix: Chained multiplicative expressions are allowed.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class MultiplicativeExpression extends AbstractJavaParser {

    private static final long serialVersionUID = 6464754895556318548L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectPart(UnaryExpression.class);
	if (acceptToken(Star.class) != null) {
	    expectPart(MultiplicativeExpression.class);
	} else if (acceptToken(Slash.class) != null) {
	    expectPart(MultiplicativeExpression.class);
	} else if (acceptToken(Percent.class) != null) {
	    expectPart(MultiplicativeExpression.class);
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
