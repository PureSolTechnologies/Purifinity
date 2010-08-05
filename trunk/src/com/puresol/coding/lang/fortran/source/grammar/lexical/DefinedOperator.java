package com.puresol.coding.lang.fortran.source.grammar.lexical;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.DefinedBinaryOp;
import com.puresol.coding.lang.fortran.source.grammar.expressions.DefinedUnaryOp;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R310 defined-operator is defined-unary-op
 * or defined-binary-op
 * or extended-intrinsic-op
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DefinedOperator extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(DefinedUnaryOp.class) != null) {
		} else if (acceptPart(DefinedBinaryOp.class) != null) {
		} else if (acceptPart(ExtendedIntrinsicOp.class) != null) {

		} else {
			abort();
		}
	}
}
