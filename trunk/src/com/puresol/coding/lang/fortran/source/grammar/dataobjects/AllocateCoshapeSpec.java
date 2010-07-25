package com.puresol.coding.lang.fortran.source.grammar.dataobjects;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.IntExpr;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R637 allocate-coshape-spec is [ lower-bound-expr : ] upper-bound-expr
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AllocateCoshapeSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(IntExpr.class);
		if (acceptToken(Colon.class) != null) {
			expectPart(IntExpr.class);
		}
		finish();
	}
}
