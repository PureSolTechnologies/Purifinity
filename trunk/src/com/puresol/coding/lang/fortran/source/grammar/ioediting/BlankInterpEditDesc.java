package com.puresol.coding.lang.fortran.source.grammar.ioediting;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.BNKeyword;
import com.puresol.coding.lang.fortran.source.keywords.BZKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1018 blank-interp-edit-desc is BN
 * or BZ
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class BlankInterpEditDesc extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(BNKeyword.class) != null) {
		} else if (acceptToken(BZKeyword.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
