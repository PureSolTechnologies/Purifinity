package com.puresol.coding.lang.fortran.source.grammar.dataobjects;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.IntExpr;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.Star;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R636 allocate-coarray-spec is [ allocate-coshape-spec-list , ] [ lower-bound-expr : ] *
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AllocateCoarraySpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(AllocateCoshapeSpecList.class) != null) {
			expectToken(Comma.class);
		}
		if (acceptPart(IntExpr.class) != null) {
			expectToken(Colon.class);
		}
		expectToken(Star.class);
		finish();
	}
}
