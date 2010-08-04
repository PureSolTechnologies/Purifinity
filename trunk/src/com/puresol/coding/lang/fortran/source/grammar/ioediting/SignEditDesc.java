package com.puresol.coding.lang.fortran.source.grammar.ioediting;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.SKeyword;
import com.puresol.coding.lang.fortran.source.keywords.SPKeyword;
import com.puresol.coding.lang.fortran.source.keywords.SSKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1017 sign-edit-desc is SS
 * or SP
 * or S
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SignEditDesc extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(SSKeyword.class) != null) {
		} else if (acceptToken(SPKeyword.class) != null) {
		} else if (acceptToken(SKeyword.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
