package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.Expr;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R472 ac-value is expr
 * or ac-implied-do
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AcValue extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(Expr.class) != null) {
		} else if (acceptPart(AcImpliedDo.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
