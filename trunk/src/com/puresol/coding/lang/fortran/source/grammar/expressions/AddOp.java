package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.symbols.Minus;
import com.puresol.coding.lang.fortran.source.symbols.Plus;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R709 add-op is +
 * or {
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AddOp extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(Plus.class) != null) {
		} else if (acceptToken(Minus.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
