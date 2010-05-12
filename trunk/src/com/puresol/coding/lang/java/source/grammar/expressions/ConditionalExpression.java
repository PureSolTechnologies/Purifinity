package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Colon;
import com.puresol.coding.lang.java.source.symbols.QuestionMark;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ConditionalExpression extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(ConditionalOrExpression.class);
		if (acceptToken(QuestionMark.class) != null) {
			expectPart(Expression.class);
			expectToken(Colon.class);
			expectPart(ConditionalExpression.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
