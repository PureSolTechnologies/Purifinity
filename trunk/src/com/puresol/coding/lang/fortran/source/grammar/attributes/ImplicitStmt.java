package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.ImplicitKeyword;
import com.puresol.coding.lang.fortran.source.keywords.NoneKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R560 implicit-stmt is IMPLICIT implicit-spec-list
 * or IMPLICIT NONE
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ImplicitStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(ImplicitKeyword.class);
		if (acceptToken(NoneKeyword.class) != null) {
		} else {
			expectPart(ImplicitSpecList.class);
		}
		finish();
	}
}
