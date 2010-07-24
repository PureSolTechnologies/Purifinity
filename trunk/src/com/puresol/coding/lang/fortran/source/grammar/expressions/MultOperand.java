package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R704 mult-operand is level-1-expr [ power-op mult-operand ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class MultOperand extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(Level1Expr.class);
		if (acceptPart(PowerOp.class) != null) {
			expectPart(MultOperand.class);
		}
		finish();
	}
}
