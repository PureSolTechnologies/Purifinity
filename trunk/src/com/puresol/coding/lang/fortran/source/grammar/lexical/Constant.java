package com.puresol.coding.lang.fortran.source.grammar.lexical;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R304 constant is literal-constant
 * or named-constant
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Constant extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(LiteralConstant.class) != null) {
		} else if (acceptPart(NamedConstant.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
