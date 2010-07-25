package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.ForAllKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R751 forall-construct-stmt is [forall-construct-name :] FORALL forall-header
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ForallConstructStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(ForallConstructName.class) != null) {
			expectToken(Colon.class);
		}
		expectToken(ForAllKeyword.class);
		expectPart(ForallHeader.class);
		finish();
	}
}
