package com.puresol.coding.lang.fortran.source.grammar.ioediting;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.DCKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DPKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1020 decimal-edit-desc is DC
 * or DP
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DecimalEditDesc extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(DCKeyword.class) != null) {
		} else if (acceptToken(DPKeyword.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
