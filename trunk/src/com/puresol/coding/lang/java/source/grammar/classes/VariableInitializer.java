package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.arrays.ArrayInitializer;
import com.puresol.coding.lang.java.source.grammar.expressions.Expression;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class VariableInitializer extends AbstractJavaParser {

	private static final long serialVersionUID = -8995105296970831547L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(Expression.class) != null) {
		} else {
			expectPart(ArrayInitializer.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}
}
