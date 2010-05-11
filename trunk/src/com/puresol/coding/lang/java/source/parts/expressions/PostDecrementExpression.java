package com.puresol.coding.lang.java.source.parts.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.MinusMinus;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class PostDecrementExpression extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(PostfixExpression.class);
		expectToken(MinusMinus.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
