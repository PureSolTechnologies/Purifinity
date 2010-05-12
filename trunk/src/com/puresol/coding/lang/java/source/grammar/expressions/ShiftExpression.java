package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.GreaterThanGreaterThan;
import com.puresol.coding.lang.java.source.symbols.GreaterThanGreaterThanGreaterThan;
import com.puresol.coding.lang.java.source.symbols.LessThanLessThan;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ShiftExpression extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(AdditiveExpression.class);
		if (acceptToken(LessThanLessThan.class) != null) {
			expectPart(ShiftExpression.class);
		} else if (acceptToken(GreaterThanGreaterThan.class) != null) {
			expectPart(ShiftExpression.class);
		} else if (acceptToken(GreaterThanGreaterThanGreaterThan.class) != null) {
			expectPart(ShiftExpression.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
