package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R735 bounds-spec is lower-bound-expr :
 * </pre>
 * 
 * for reference, but not used:
 * 
 * <pre>
 * R634 lower-bound-expr is scalar-int-expr
 * R635 upper-bound-expr is scalar-int-expr
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class BoundsSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(IntExpr.class);
		expectToken(Colon.class);
		finish();
	}
}
