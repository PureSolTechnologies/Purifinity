package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R722 expr is [ expr defined-binary-op ] level-5-expr
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Expr extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(Level5Expr.class);
		if (acceptPart(DefinedBinaryOp.class) != null) {
			expectPart(Expr.class);
		}
		finish();
	}
}
