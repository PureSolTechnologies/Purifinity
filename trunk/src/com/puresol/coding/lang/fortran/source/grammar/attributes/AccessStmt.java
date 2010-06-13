package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R524 access-stmt is access-spec [ [ :: ] access-id -list ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AccessStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(AccessSpec.class);
		if (acceptToken(Colon.class) != null) {
			expectToken(Colon.class);
			expectPart(AccessIdList.class);
		} else if (acceptPart(AccessIdList.class) != null) {
		}
		finish();
	}
}
