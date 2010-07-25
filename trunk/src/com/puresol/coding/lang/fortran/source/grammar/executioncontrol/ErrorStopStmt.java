package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.ErrorKeyword;
import com.puresol.coding.lang.fortran.source.keywords.StopKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R855 stop-stmt is STOP [ stop-code ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ErrorStopStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(ErrorKeyword.class);
		expectToken(StopKeyword.class);
		acceptPart(StopCode.class);
		finish();
	}
}
