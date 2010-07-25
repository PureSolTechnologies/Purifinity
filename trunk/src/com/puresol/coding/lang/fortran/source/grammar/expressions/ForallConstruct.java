package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R750 forall-construct is forall-construct-stmt
 * [forall-body-construct ] ...
 * end-forall-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ForallConstruct extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(ForallConstructStmt.class);
		while (acceptPart(ForallBodyConstruct.class) != null)
			;
		expectPart(EndForallStmt.class);
		finish();
	}
}
