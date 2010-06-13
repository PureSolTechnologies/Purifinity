package com.puresol.coding.lang.fortran.source.grammar.attrspecdecl;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.InKeyword;
import com.puresol.coding.lang.fortran.source.keywords.InOutKeyword;
import com.puresol.coding.lang.fortran.source.keywords.OutKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Star;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R523 intent-spec is IN
 * or OUT
 * or INOUT
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class IntentSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(InKeyword.class) != null) {
		} else if (acceptToken(OutKeyword.class) != null) {
		} else if (acceptToken(InOutKeyword.class) != null) {
		} else {
			abort();
		}
		expectToken(Star.class);
		finish();
	}
}
