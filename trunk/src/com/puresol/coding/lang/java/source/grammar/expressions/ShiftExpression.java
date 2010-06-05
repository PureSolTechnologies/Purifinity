package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * shiftExpression : additiveExpression (shiftOp additiveExpression )* ;
 * 
 * Fixed chaining!
 * 
 * shiftOp 
    :    '<' '<'
    |    '>' '>' '>'
    |    '>' '>'
    ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ShiftExpression extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(AdditiveExpression.class);
		if (acceptPart(LessThanLessThan.class) != null) {
			expectPart(ShiftExpression.class);
		} else if (acceptPart(GreaterThanGreaterThanGreaterThan.class) != null) {
			expectPart(ShiftExpression.class);
		} else if (acceptPart(GreaterThanGreaterThan.class) != null) {
			expectPart(ShiftExpression.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
