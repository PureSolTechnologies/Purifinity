package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Minus;
import com.puresol.coding.lang.java.source.symbols.MinusMinus;
import com.puresol.coding.lang.java.source.symbols.Plus;
import com.puresol.coding.lang.java.source.symbols.PlusPlus;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * 
 * NOTE: for '+' and '-', if the next token is int or long interal, then it's
 * not a unary expression. it's a literal with signed value. INTLTERAL AND LONG
 * LITERAL are added here for this.
 * 
 * unaryExpression : '+' unaryExpression | '-' unaryExpression | '++'
 * unaryExpression | '--' unaryExpression | unaryExpressionNotPlusMinus ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class UnaryExpression extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(Plus.class) != null) {
			expectPart(UnaryExpression.class);
		} else if (acceptToken(Minus.class) != null) {
			expectPart(UnaryExpression.class);
		} else if (acceptToken(PlusPlus.class) != null) {
			expectPart(UnaryExpression.class);
		} else if (acceptToken(MinusMinus.class) != null) {
			expectPart(UnaryExpression.class);
		} else if (acceptPart(UnaryExpressionNotPlusMinus.class) != null) {
		} else {
			abort();
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
