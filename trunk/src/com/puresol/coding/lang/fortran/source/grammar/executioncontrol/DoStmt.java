package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R815 do-stmt is label-do-stmt
 * or nonlabel-do-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DoStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(LabelDoStmt.class) != null) {
		} else if (acceptPart(NonlabelDoStmt.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
