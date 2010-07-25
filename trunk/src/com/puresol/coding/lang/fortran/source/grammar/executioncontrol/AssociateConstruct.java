package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R802 associate-construct is associate-stmt
 * block
 * end-associate-stmt
 * or {
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AssociateConstruct extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(AssociateStmt.class);
		expectPart(Block.class);
		expectPart(EndAssociateStmt.class);
		finish();
	}
}
