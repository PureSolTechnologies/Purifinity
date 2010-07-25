package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R832 if-construct is if-then-stmt
 * block
 * [ else-if-stmt
 * block ] ...
 * [ else-stmt
 * block ]
 * end-if-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class IfConstruct extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(IfThenStmt.class);
		expectPart(Block.class);
		while (acceptPart(ElseIfStmt.class) != null) {
			expectPart(Block.class);
		}
		if (acceptPart(ElseStmt.class) != null) {
			expectPart(Block.class);
		}
		expectPart(EndIfStmt.class);
		finish();
	}
}
