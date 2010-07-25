package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R810 critical-construct is critical-stmt
 * block
 * end-critical-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CriticalConstruct extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(CriticalStmt.class);
		expectPart(Block.class);
		expectPart(EndCriticalStmt.class);
		finish();
	}
}
