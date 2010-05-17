package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.PrimitiveType;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.Type;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * castExpression : '(' primitiveType ')' unaryExpression | '(' type ')'
 * unaryExpressionNotPlusMinus ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CastExpression extends AbstractJavaParser {

    private static final long serialVersionUID = 6464754895556318548L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectToken(LParen.class);
	if (acceptPart(PrimitiveType.class) != null) {
	    acceptPart(Dims.class);
	    expectToken(RParen.class);
	    expectPart(UnaryExpression.class);
	} else {
	    expectPart(Type.class);
	    expectToken(RParen.class);
	    expectPart(UnaryExpressionNotPlusMinus.class);
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
