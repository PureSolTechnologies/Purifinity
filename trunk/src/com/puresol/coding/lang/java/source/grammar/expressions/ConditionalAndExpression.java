package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.LogicalAnd;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ConditionalAndExpression extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(InclusiveOrExpression.class);
		if (acceptToken(LogicalAnd.class) != null) {
			expectPart(ConditionalAndExpression.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
