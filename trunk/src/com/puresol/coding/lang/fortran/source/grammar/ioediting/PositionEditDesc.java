package com.puresol.coding.lang.fortran.source.grammar.ioediting;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.TKeyword;
import com.puresol.coding.lang.fortran.source.keywords.TLKeyword;
import com.puresol.coding.lang.fortran.source.keywords.TRKeyword;
import com.puresol.coding.lang.fortran.source.keywords.XKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1015 position-edit-desc is T n
 * or TL n
 * or TR n
 * or n X
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PositionEditDesc extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(TKeyword.class) != null) {
			expectPart(N.class);
		} else if (acceptToken(TLKeyword.class) != null) {
			expectPart(N.class);
		} else if (acceptToken(TRKeyword.class) != null) {
			expectPart(N.class);
		} else if (acceptPart(N.class) != null) {
			expectToken(XKeyword.class);
		} else {
			abort();
		}
		finish();
	}

}
