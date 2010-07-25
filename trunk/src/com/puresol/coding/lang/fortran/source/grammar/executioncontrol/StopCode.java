package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.DefaultCharConstantExpr;
import com.puresol.coding.lang.fortran.source.grammar.expressions.IntConstantExpr;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R857 stop-code is scalar-default-char-constant-expr
 * or scalar-int-constant-expr
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class StopCode extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(DefaultCharConstantExpr.class) != null) {
		} else if (acceptPart(IntConstantExpr.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
