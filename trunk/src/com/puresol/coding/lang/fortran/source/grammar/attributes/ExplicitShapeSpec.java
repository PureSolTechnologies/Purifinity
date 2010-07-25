package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.SpecificationExpr;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R516 explicit-shape-spec is [ lower-bound : ] upper-bound
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ExplicitShapeSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(SpecificationExpr.class);
		if (acceptToken(Colon.class) != null) {
			expectPart(SpecificationExpr.class);
		}
		finish();
	}
}
