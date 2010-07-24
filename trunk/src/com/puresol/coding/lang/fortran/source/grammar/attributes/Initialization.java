package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.ConstantExpr;
import com.puresol.coding.lang.fortran.source.grammar.types.InitialDataTarget;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.coding.lang.fortran.source.symbols.GreaterThan;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R505 initialization is = constant-expr
 * or => null-init
 * or => initial-data-target
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Initialization extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(Equals.class);
		if (acceptPart(ConstantExpr.class) != null) {
		} else {
			expectToken(GreaterThan.class);
			if (acceptPart(NullInit.class) != null) {
			} else {
				expectPart(InitialDataTarget.class);
			}
		}
		finish();
	}
}
