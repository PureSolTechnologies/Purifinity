package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R706 level-2-expr is [ [ level-2-expr ] add-op ] add-operand
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Level2Expr extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(AddOperand.class);
		if (acceptPart(AddOp.class) != null) {
			expectPart(Level2Expr.class);
		}
		finish();
	}
}
