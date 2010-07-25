package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R838 case-construct is select-case-stmt
 * [ case-stmt
 * block ] ...
 * end-select-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CaseConstruct extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(SelectCaseStmt.class);
		while (acceptPart(CaseStmt.class) != null) {
			expectPart(Block.class);
		}
		expectPart(EndSelectStmt.class);
		finish();
	}
}
