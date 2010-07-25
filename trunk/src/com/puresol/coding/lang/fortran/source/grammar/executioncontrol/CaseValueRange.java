package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.Expr;
import com.puresol.coding.lang.fortran.source.keywords.DefaultKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R844 case-value-range is case-value
 * or case-value :
 * or : case-value
 * or case-value : case-value
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CaseValueRange extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(Colon.class)) {
			expectPart(CaseValue.class);
		} else {
			expectPart(CaseValue.class);
			if (acceptToken(Colon.class) != null) {
				acceptToken(CaseValue.class);
			}
		}
		finish();
	}
}
