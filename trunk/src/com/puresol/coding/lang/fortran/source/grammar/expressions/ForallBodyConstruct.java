package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R756 forall-body-construct is forall-assignment-stmt
 * or where-stmt
 * or where-construct
 * or forall-construct
 * or forall-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ForallBodyConstruct extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(ForallAssignmentStmt.class) != null) {
		} else if (acceptPart(WhereStmt.class) != null) {
		} else if (acceptPart(WhereConstruct.class) != null) {
		} else if (acceptPart(ForallConstruct.class) != null) {
		} else if (acceptPart(ForallStmt.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
