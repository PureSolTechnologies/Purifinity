package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R712 level-4-expr is [ level-3-expr rel-op ] level-3-expr
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Level4Expr extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(Level3Expr.class);
		if (acceptPart(RelOp.class) != null) {
			expectPart(Level3Expr.class);
		}
		finish();
	}
}
