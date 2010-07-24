package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R713 rel-op is .EQ.
 * or .NE.
 * or .LT.
 * or .LE.
 * or .GT.
 * or .GE.
 * or ==
 * or /=
 * or <
 * or <=
 * or >
 * or >=
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class RelOp extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(EqualOp.class) != null) {
		} else if (acceptPart(UnequalOp.class) != null) {
		} else if (acceptPart(LessOrEqualOp.class) != null) {
		} else if (acceptPart(LessThanOp.class) != null) {
		} else if (acceptPart(GreaterOrEqualOp.class) != null) {
		} else if (acceptPart(GreaterThanOp.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
