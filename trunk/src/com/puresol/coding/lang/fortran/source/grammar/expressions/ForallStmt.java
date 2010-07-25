package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.ForAllKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R759 forall-stmt is FORALL forall-header forall-assignment-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ForallStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(ForAllKeyword.class);
		expectPart(ForallHeader.class);
		expectPart(ForallAssignmentStmt.class);
		finish();
	}
}
