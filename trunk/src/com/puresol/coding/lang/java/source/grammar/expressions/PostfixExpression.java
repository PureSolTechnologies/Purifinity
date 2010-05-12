package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.ExpressionName;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class PostfixExpression extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(Primary.class) != null) {
		} else if (acceptPart(ExpressionName.class) != null) {
		} else if (acceptPart(PostIncrementExpression.class) != null) {
		} else {
			expectPart(PostDecrementExpression.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
