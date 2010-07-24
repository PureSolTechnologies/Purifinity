package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R705 add-operand is [ add-operand mult-op ] mult-operand
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AddOperand extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(MultOperand.class);
		if (acceptPart(MultOp.class) != null) {
			expectPart(AddOperand.class);
		}
		finish();
	}
}
