package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R814 block-do-construct is do-stmt
 * do-block
 * end-do
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class BlockDoConstruct extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(DoStmt.class);
		expectPart(DoBlock.class);
		expectPart(EndDoStmt.class);
		finish();
	}
}
