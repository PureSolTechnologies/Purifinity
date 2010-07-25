package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R757 forall-assignment-stmt is assignment-stmt
 * or pointer-assignment-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ForallAssignmentStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(AssignmentStmt.class) != null) {
		} else if (acceptPart(PointerAssignmentStmt.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
