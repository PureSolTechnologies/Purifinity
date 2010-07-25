package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.DefaultKeyword;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R843 case-selector is ( case-value-range-list )
 * or DEFAULT
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CaseSelector extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(LParen.class) != null) {
			expectPart(CaseValueRangeList.class);
			expectToken(RParen.class);
		} else if (acceptToken(DefaultKeyword.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
