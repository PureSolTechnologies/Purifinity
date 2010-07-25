package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R829 inner-shared-do-construct is label-do-stmt
 * do-body
 * do-term-shared-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class InnerSharedDoConstruct extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(LabelDoStmt.class);
		expectPart(DoBody.class);
		expectPart(DoTermSharedStmt.class);
		finish();
	}
}
