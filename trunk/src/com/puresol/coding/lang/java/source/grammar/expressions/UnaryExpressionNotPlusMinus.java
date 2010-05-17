package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.MinusMinus;
import com.puresol.coding.lang.java.source.symbols.Not;
import com.puresol.coding.lang.java.source.symbols.PlusPlus;
import com.puresol.coding.lang.java.source.symbols.Tilde;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * unaryExpressionNotPlusMinus 
 *     :   '~' unaryExpression
 *     |   '!' unaryExpression
 *     |   castExpression
 *     |   primary
 *         (selector
 *         )*
 *         (   '++'
 *         |   '--'
 *         )?
 *     ;
 * </pre>
 * 
 * @author rludwig
 * 
 */
public class UnaryExpressionNotPlusMinus extends AbstractJavaParser {

    private static final long serialVersionUID = 6464754895556318548L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptToken(Tilde.class) != null) {
	    expectPart(UnaryExpression.class);
	} else if (acceptToken(Not.class) != null) {
	    expectPart(UnaryExpression.class);
	} else if (acceptPart(CastExpression.class) != null) {
	} else if (acceptPart(Primary.class) != null) {
	    while (acceptPart(Selector.class) != null)
		;
	    if (acceptToken(PlusPlus.class) != null) {
	    } else if (acceptToken(MinusMinus.class) != null) {
	    }
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
