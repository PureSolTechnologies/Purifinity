package com.puresol.coding.lang.fortran.source.grammar.dataobjects;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.Expr;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R602 variable is designator
 * or expr
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Variable extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(Designator.class) != null) {
		} else if (acceptPart(Expr.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
