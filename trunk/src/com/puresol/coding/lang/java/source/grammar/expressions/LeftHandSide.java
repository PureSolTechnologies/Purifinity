package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.ExpressionName;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class LeftHandSide extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(ExpressionName.class);
		expectPart(FieldAccess.class);
		expectPart(ArrayAccess.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
