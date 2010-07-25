package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.highlevel.SpecificationPart;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R807 block-construct is block-stmt
 * [ specification-part ]
 * block
 * end-block-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class BlockConstruct extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(BlockStmt.class);
		acceptPart(SpecificationPart.class);
		expectPart(Block.class);
		expectPart(EndBlockStmt.class);
		finish();
	}
}
