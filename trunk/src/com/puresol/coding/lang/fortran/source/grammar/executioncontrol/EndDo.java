package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R821 end-do is end-do-stmt
 * or continue-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EndDo extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(EndDoStmt.class) != null) {
		} else if (acceptPart(ContinueStmt.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
