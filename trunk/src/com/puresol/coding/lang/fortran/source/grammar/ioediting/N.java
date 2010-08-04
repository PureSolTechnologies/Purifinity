package com.puresol.coding.lang.fortran.source.grammar.ioediting;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.types.IntLiteralConstant;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1016 n is int-literal-constant
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class N extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(IntLiteralConstant.class);
		finish();
	}
}
