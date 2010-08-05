package com.puresol.coding.lang.fortran.source.grammar.lexical;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R311 extended-intrinsic-op is intrinsic-operator
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ExtendedIntrinsicOp extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(IntrinsicOperator.class);
		finish();
	}
}
