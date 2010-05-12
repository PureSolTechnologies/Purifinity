package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Not;
import com.puresol.coding.lang.java.source.symbols.Tilde;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class UnaryExpressionNotPlusMinus extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(PostfixExpression.class) != null) {
		} else if (acceptToken(Tilde.class) != null) {
			expectPart(UnaryExpression.class);
		} else if (acceptToken(Not.class) != null) {
			expectPart(UnaryExpression.class);
		} else {
			expectPart(CastExpression.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
