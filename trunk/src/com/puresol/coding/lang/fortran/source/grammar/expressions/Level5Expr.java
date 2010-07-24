package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R717 level-5-expr is [ level-5-expr equiv-op ] equiv-operand
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Level5Expr extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(EquivOperand.class);
		if (acceptPart(EquivOp.class) != null) {
			expectPart(Level5Expr.class);
		}
		finish();
	}
}
