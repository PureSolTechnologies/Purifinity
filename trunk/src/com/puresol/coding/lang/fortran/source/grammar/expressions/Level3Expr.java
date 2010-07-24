package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R710 level-3-expr is [ level-3-expr concat-op ] level-2-expr
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Level3Expr extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(Level2Expr.class);
		if (acceptPart(ConcatOp.class) != null) {
			expectPart(Level3Expr.class);
		}
		finish();
	}
}
