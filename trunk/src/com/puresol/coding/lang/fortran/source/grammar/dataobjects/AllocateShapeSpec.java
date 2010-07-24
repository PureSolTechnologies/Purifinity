package com.puresol.coding.lang.fortran.source.grammar.dataobjects;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.types.TypeSpec;
import com.puresol.coding.lang.fortran.source.keywords.AllocateKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R633 allocate-shape-spec is [ lower-bound-expr : ] upper-bound-expr
 * </pre>
 * 
 * for reference, but not used:
 * 
 * <pre>
 * R636 allocate-coarray-spec is [ allocate-coshape-spec-list , ] [ lower-bound-expr : ] *
 * R637 allocate-coshape-spec is [ lower-bound-expr : ] upper-bound-expr
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AllocateShapeSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		/*
		 * LowerBoundExpr and UpperBoundExpr are ScalarIntExp and were not
		 * created due to ambigous conditions.
		 */
		expectPart(ScalarIntExpr.class);
		if (acceptToken(Colon.class) != null) {
			expectPart(ScalarIntExpr.class);
		}
		finish();
	}
}
