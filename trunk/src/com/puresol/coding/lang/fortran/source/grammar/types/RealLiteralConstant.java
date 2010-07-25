package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.literals.RealLiteral;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R413 real-literal-constant is signicand [ exponent-letter exponent ] [ kind-param ]
 * or digit-string exponent-letter exponent [ kind-param ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class RealLiteralConstant extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(RealLiteral.class);
		finish();
	}
}
