package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.symbols.Star;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R540 data-stmt-value is [ data-stmt-repeat * ] data-stmt-constant
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DataStmtValue extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(DataStmtRepeat.class) != null) {
			expectToken(Star.class);
		}
		expectPart(DataStmtConstant.class);
		finish();
	}
}
