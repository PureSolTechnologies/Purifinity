package com.puresol.coding.lang.fortran.source.grammar.iostatements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.executioncontrol.DoVariable;
import com.puresol.coding.lang.fortran.source.grammar.expressions.ScalarIntExpr;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R920 io-implied-do-control is do-variable = scalar-int-expr ,
 * scalar-int-expr [ , scalar-int-expr ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class IoImpliedDoControl extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(DoVariable.class);
		expectToken(Equals.class);
		expectPart(ScalarIntExpr.class);
		expectToken(Comma.class);
		expectPart(ScalarIntExpr.class);
		if (acceptToken(Comma.class) != null) {
			expectPart(ScalarIntExpr.class);
		}
		finish();
	}

}
