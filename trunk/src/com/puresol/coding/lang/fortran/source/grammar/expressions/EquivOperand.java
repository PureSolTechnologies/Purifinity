package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R716 equiv-operand is [ equiv-operand or-op ] or-operand
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EquivOperand extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(OrOperand.class);
		if (acceptPart(OrOp.class) != null) {
			expectPart(EquivOperand.class);
		}
		finish();
	}
}
