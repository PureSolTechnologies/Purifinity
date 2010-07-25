package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.SpecificationExpr;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.Star;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R511 explicit-coshape-spec is [ [ lower-cobound : ] upper-cobound, ]...
 * [ lower-cobound : ] *
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ExplicitCoshapeSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		while (acceptPart(SpecificationExpr.class) != null) {
			if (acceptToken(Colon.class) != null) {
				if (acceptPart(SpecificationExpr.class) != null) {
					break;
				}
			}
			expectToken(Comma.class);
		}
		expectToken(Star.class);
		finish();
	}
}
